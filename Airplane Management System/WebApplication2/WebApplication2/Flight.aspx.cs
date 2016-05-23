using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Telerik.Web.UI;

namespace WebApplication2
{
    public partial class Flight : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        public DataTable GetDataTable()
        {
            string arrivalcode, deparcode, noofconnections,query="";
            arrivalcode = Arrivalcode.SelectedValue.ToString();
            deparcode = departurecode.SelectedValue.ToString();
            noofconnections = numberof.SelectedValue.ToString();
            String ConnString = ConfigurationManager.ConnectionStrings["AMP3ConnectionString"].ToString();
            SqlConnection conn = new SqlConnection(ConnString);
            SqlDataAdapter adapter = new SqlDataAdapter();
            DataTable myDataTable = new DataTable();

           
            switch (noofconnections)
            {
                case "0":
                    query = "Select FLIGHT_NUMBER,AIRLINE,WEEKDAYS,SCHEDULED_DEPARTURE_TIME,SCHEDULED_ARRIVAL_TIME FROM FLIGHT WHERE DEPARTURE_AIRPORT_CODE='" + deparcode + "' AND ARRIVAL_AIRPORT_CODE='" + arrivalcode + "'";

                    adapter.SelectCommand = new SqlCommand(query, conn);

                    try
                    {
                        adapter.Fill(myDataTable);
                    }
                    finally
                    {
                        conn.Close();
                    }
                    break;

                case "1":
                    query = "Select * FROM FLIGHT WHERE DEPARTURE_AIRPORT_CODE='" + deparcode + "'";
                    DataTable dt = new DataTable();

                    SqlDataAdapter da = new SqlDataAdapter(query, conn);

                    DataColumn datacol1 = myDataTable.Columns.Add("Flight_number");
                    datacol1.DataType = typeof(string);
                    DataColumn datacol2 = myDataTable.Columns.Add("ARRIVAL_AIRPORT_CODE");
                    datacol2.DataType = typeof(string);
                    DataColumn datacol3 = myDataTable.Columns.Add("DEPARTURE_AIRPORT_CODE");
                    datacol3.DataType = typeof(string);
                    DataColumn datacol4 = myDataTable.Columns.Add("Arrival_time");
                    datacol4.DataType = typeof(string);
                    DataColumn datacol5 = myDataTable.Columns.Add("Departure_time");
                    datacol5.DataType = typeof(string);
                    DataColumn datacol7 = myDataTable.Columns.Add("Weekdays");
                    datacol7.DataType = typeof(string);
                    DataColumn datacol8 = myDataTable.Columns.Add("Airlines");
                    datacol8.DataType = typeof(string);


                    da.Fill(dt);
                    int i;
                    for (i = 0; i < dt.Rows.Count; i++)
                    {

                        string acode = dt.Rows[i]["ARRIVAL_AIRPORT_CODE"].ToString();
                        DateTime d1 = new DateTime();
                        d1 = Convert.ToDateTime(dt.Rows[i]["SCHEDULED_ARRIVAL_TIME"].ToString());

                        query = "Select * FROM FLIGHT WHERE DEPARTURE_AIRPORT_CODE='" + acode + "'";
                        DataTable dt1 = new DataTable();

                        SqlDataAdapter da1 = new SqlDataAdapter(query, conn);
                        da1.Fill(dt1);
                        for (int j = 0; j < dt1.Rows.Count; j++)
                        {
                            if (dt1.Rows[j]["ARRIVAL_AIRPORT_CODE"].ToString().Trim().Equals(arrivalcode.Trim()))
                            {
                                List<string> l1 = new List<string>();
                                var weekdays1 = dt.Rows[i]["WEEKDAYS"].ToString().Split('_');
                                foreach (var wrd in weekdays1)
                                {
                                    l1.Add(wrd);
                                }
                                List<string> l2 = new List<string>();
                                var weekdays2 = dt1.Rows[j]["WEEKDAYS"].ToString().Split('_');
                                foreach (var wrd in weekdays2)
                                {
                                    l2.Add(wrd);
                                }
                                var inboth = l1.Intersect(l2).ToList();
                                if (inboth.Count() > 0)
                                {
                                    DateTime d2 = new DateTime();
                                    d2 = Convert.ToDateTime(dt1.Rows[j]["SCHEDULED_DEPARTURE_TIME"].ToString());
                                    int min = (d2 - d1).Hours;
                                    if (min >= 1)
                                    {

                                        var str = String.Join(",", inboth);
                                        myDataTable.Rows.Add(dt.Rows[i]["FLIGHT_NUMBER"].ToString(), acode, dt.Rows[i]["DEPARTURE_AIRPORT_CODE"].ToString(), dt.Rows[i]["SCHEDULED_ARRIVAL_TIME"].ToString(), dt.Rows[i]["SCHEDULED_DEPARTURE_TIME"].ToString(), str, dt.Rows[i]["AIRLINE"].ToString());
                                        myDataTable.Rows.Add(dt1.Rows[j]["FLIGHT_NUMBER"].ToString(), dt1.Rows[j]["ARRIVAL_AIRPORT_CODE"].ToString(), dt1.Rows[j]["DEPARTURE_AIRPORT_CODE"].ToString(), dt1.Rows[j]["SCHEDULED_ARRIVAL_TIME"].ToString(), dt1.Rows[j]["SCHEDULED_DEPARTURE_TIME"].ToString(), str, dt1.Rows[j]["AIRLINE"].ToString());
                                        DataRow myRow = myDataTable.NewRow();
                                        myDataTable.Rows.Add(myRow);
                                    }

                                }

                            }
                        }
                    }
                    conn.Close();
                    break;
                case "2":
                     query = "Select * FROM FLIGHT WHERE DEPARTURE_AIRPORT_CODE='" + deparcode + "'";
                    DataTable t1 = new DataTable();

                    SqlDataAdapter a1 = new SqlDataAdapter(query, conn);

                    DataColumn col1 = myDataTable.Columns.Add("Flight_number");
                    col1.DataType = typeof(string);
                    DataColumn col2 = myDataTable.Columns.Add("ARRIVAL_AIRPORT_CODE");
                    col2.DataType = typeof(string);
                    DataColumn col3 = myDataTable.Columns.Add("DEPARTURE_AIRPORT_CODE");
                    col3.DataType = typeof(string);
                    DataColumn col4 = myDataTable.Columns.Add("Arrival_time");
                    col4.DataType = typeof(string);
                    DataColumn col5 = myDataTable.Columns.Add("Departure_time");
                    col5.DataType = typeof(string);
                    DataColumn col7 = myDataTable.Columns.Add("Weekdays");
                    col7.DataType = typeof(string);
                    DataColumn col8 = myDataTable.Columns.Add("Airlines");
                    col8.DataType = typeof(string);


                    a1.Fill(t1);
                    int k;
                    for (k = 0; k < t1.Rows.Count; k++)
                    {

                        string acode = t1.Rows[k]["ARRIVAL_AIRPORT_CODE"].ToString();
                        DateTime d1 = new DateTime();
                        d1 = Convert.ToDateTime(t1.Rows[k]["SCHEDULED_ARRIVAL_TIME"].ToString());

                        query = "Select * FROM FLIGHT WHERE DEPARTURE_AIRPORT_CODE='" + acode + "'";
                        DataTable dt1 = new DataTable();

                        SqlDataAdapter da1 = new SqlDataAdapter(query, conn);
                        da1.Fill(dt1);
                        for (int j = 0; j < dt1.Rows.Count; j++)
                        {
                            string acode1 = dt1.Rows[j]["ARRIVAL_AIRPORT_CODE"].ToString();
                            DateTime d2 = new DateTime();
                            d2 = Convert.ToDateTime(dt1.Rows[j]["SCHEDULED_DEPARTURE_TIME"].ToString());

                            query = "Select * FROM FLIGHT WHERE DEPARTURE_AIRPORT_CODE='" + acode1 + "'";
                            DataTable dt2 = new DataTable();

                            SqlDataAdapter da2 = new SqlDataAdapter(query, conn);
                            da2.Fill(dt2);
                            for (int m = 0; m < dt2.Rows.Count; m++)
                            {
                                if (dt2.Rows[m]["ARRIVAL_AIRPORT_CODE"].ToString().Trim().Equals(arrivalcode.Trim()))
                                {
                                    List<string> l1 = new List<string>();
                                    var weekdays1 = t1.Rows[k]["WEEKDAYS"].ToString().Split('_');
                                    foreach (var wrd in weekdays1)
                                    {
                                        l1.Add(wrd);
                                    }
                                    List<string> l2 = new List<string>();
                                    var weekdays2 = dt1.Rows[j]["WEEKDAYS"].ToString().Split('_');
                                    foreach (var wrd in weekdays2)
                                    {
                                        l2.Add(wrd);
                                    }
                                    List<string> l3 = new List<string>();
                                    var weekdays3 = dt2.Rows[m]["WEEKDAYS"].ToString().Split('_');
                                    foreach (var wrd in weekdays3)
                                    {
                                        l3.Add(wrd);
                                    }
                                    var inboth = l1.Intersect(l2).Intersect(l3).ToList();
                                    if (inboth.Count() > 0)
                                    {
                                        DateTime d3 = new DateTime();
                                        d3 = Convert.ToDateTime(dt2.Rows[m]["SCHEDULED_DEPARTURE_TIME"].ToString());
                                        DateTime d4 = new DateTime();
                                        d4 = Convert.ToDateTime(dt1.Rows[j]["SCHEDULED_ARRIVAL_TIME"].ToString());
                                        int min = (d2 - d1).Hours;
                                        int min1 = (d3 - d4).Hours;
                                        if ((min >= 1  && min <=24) && (min1>=1 && min1<=24))
                                        {

                                            var str = String.Join(",", inboth);
                                            myDataTable.Rows.Add(t1.Rows[k]["FLIGHT_NUMBER"].ToString(), acode, t1.Rows[k]["DEPARTURE_AIRPORT_CODE"].ToString(), t1.Rows[k]["SCHEDULED_ARRIVAL_TIME"].ToString(), t1.Rows[k]["SCHEDULED_DEPARTURE_TIME"].ToString(), str, t1.Rows[k]["AIRLINE"].ToString());
                                            myDataTable.Rows.Add(dt1.Rows[j]["FLIGHT_NUMBER"].ToString(), dt1.Rows[j]["ARRIVAL_AIRPORT_CODE"].ToString(), dt1.Rows[j]["DEPARTURE_AIRPORT_CODE"].ToString(), dt1.Rows[j]["SCHEDULED_ARRIVAL_TIME"].ToString(), dt1.Rows[j]["SCHEDULED_DEPARTURE_TIME"].ToString(), str, dt1.Rows[j]["AIRLINE"].ToString());
                                            myDataTable.Rows.Add(dt2.Rows[m]["FLIGHT_NUMBER"].ToString(), dt2.Rows[m]["ARRIVAL_AIRPORT_CODE"].ToString(), dt2.Rows[m]["DEPARTURE_AIRPORT_CODE"].ToString(), dt2.Rows[m]["SCHEDULED_ARRIVAL_TIME"].ToString(), dt2.Rows[m]["SCHEDULED_DEPARTURE_TIME"].ToString(), str, dt2.Rows[m]["AIRLINE"].ToString());
                                            DataRow myRow = myDataTable.NewRow();
                                            myDataTable.Rows.Add(myRow);
                                        }

                                    }

                                }
                            }
                        }
                    }
                    conn.Close();
                    break;

                
            }
            
                    return myDataTable;
            
             
        }
    
        protected void RadButton2_Click(object sender, EventArgs e)
        {
            string arrivalcode, deparcode;
            arrivalcode = Arrivalcode.SelectedValue.ToString();
            deparcode = departurecode.SelectedValue.ToString();
            if (arrivalcode == deparcode)
            {
                l12.Text = "Arrival and departure code cannot be same.";
                RadGrid2.Visible = false;
            }
            else
            {
                l12.Text = "";
                RadGrid2.Visible = true;
                RadGrid2.DataSource = GetDataTable();
                RadGrid2.Rebind();
            }
        }
        protected void RadGrid2_NeedDataSource(object sender, Telerik.Web.UI.GridNeedDataSourceEventArgs e)
        {
            (sender as RadGrid).DataSource = GetDataTable();
        }
    }
}