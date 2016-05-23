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
    public partial class Passenger : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void RadGrid2_NeedDataSource(object sender, Telerik.Web.UI.GridNeedDataSourceEventArgs e)
        {
            (sender as RadGrid).DataSource = GetDataTable();
        }

        public DataTable GetDataTable()
        {
            string fnum = flightnumber.Text;
            int num1;
            bool res = int.TryParse(fnum, out num1);
            if (res == false)
            {
                fnum = "";
            }
            string query = "SELECT CUSTOMER_NAME,CUSTOMER_PHONE,FLIGHT_NUMBER,SEAT_NUMBER FROM SEAT_RESERVATION where FLIGHT_NUMBER='" + fnum + "' AND DATE='" + RadDatePicker1.SelectedDate.Value + "'";

            String ConnString = ConfigurationManager.ConnectionStrings["AMP3ConnectionString"].ToString();
            SqlConnection conn = new SqlConnection(ConnString);
            SqlDataAdapter adapter = new SqlDataAdapter();
            adapter.SelectCommand = new SqlCommand(query, conn);

            DataTable myDataTable = new DataTable();

            conn.Open();
            try
            {
                adapter.Fill(myDataTable);
            }
            finally
            {
                conn.Close();
            }

            return myDataTable;
        }

        protected void RadButton1_Click(object sender, EventArgs e)
        {
            bool hasValue = RadDatePicker1.SelectedDate.HasValue;
            if (!hasValue)
            {
                Label2.Text = "Please Enter date";
                RadGrid2.Visible = false;
            }
            else if (string.IsNullOrWhiteSpace(flightnumber.Text))
            {
                Label1.Text = "Please enter flight number";
                RadGrid2.Visible = false;
            }
            else{
                Label1.Text = "";
                Label2.Text = "";
                Label3.Text = "";
                RadGrid2.Visible = true;
                RadGrid2.DataSource = GetDataTable();
                RadGrid2.Rebind();
            }
        }

        protected void RadButton2_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(custname.Text))
            {
                Label3.Text = "Please enter customer name";
                RadGrid2.Visible = false;
            }
            else
            {
                Label1.Text = "";
                Label2.Text = "";
                Label3.Text = "";
                RadGrid2.Visible = true;
                RadGrid2.DataSource = getCustTable();
                RadGrid2.Rebind();
            }
        }
        protected DataTable getCustTable()
        {
            string cname = custname.Text;

            string query = "SELECT SEAT_RESERVATION.FLIGHT_NUMBER,CONVERT(VARCHAR(10),SEAT_RESERVATION.DATE,101) AS JOURNEY_DATE,SEAT_RESERVATION.SEAT_NUMBER,FLIGHT.SCHEDULED_ARRIVAL_TIME,FLIGHT.SCHEDULED_DEPARTURE_TIME,FLIGHT.ARRIVAL_AIRPORT_CODE,FLIGHT.DEPARTURE_AIRPORT_CODE from SEAT_RESERVATION  inner join FLIGHT on SEAT_RESERVATION.FLIGHT_NUMBER=FLIGHT.FLIGHT_NUMBER where CUSTOMER_NAME='" + cname + "'";

            String ConnString = ConfigurationManager.ConnectionStrings["AMP3ConnectionString"].ToString();
            SqlConnection conn = new SqlConnection(ConnString);
            SqlDataAdapter adapter = new SqlDataAdapter();
            adapter.SelectCommand = new SqlCommand(query, conn);

            DataTable myDataTable = new DataTable();

            conn.Open();
            try
            {
                adapter.Fill(myDataTable);
            }
            finally
            {
                conn.Close();
            }

            return myDataTable;
        }
        protected void flight_CheckedChanged(object sender, EventArgs e)
        {
            if (flight.Checked)
            {
                flight_data.Visible = true;
                customerInfo.Visible = false;

            }
            else if(cust.Checked)
            {
                flight_data.Visible = false;
                customerInfo.Visible = true;

            }
            else
            {
                flight_data.Visible = false;
                customerInfo.Visible = false;
            }
            ClearControls();
        }

        protected void ClearControls()
        {
            Label1.Text = "";
            Label2.Text = "";
            Label3.Text = "";
            custname.Text = "";
            flightnumber.Text = "";
            RadDatePicker1.Clear();
            RadGrid2.Visible = false;
        }

        protected void cust_CheckedChanged1(object sender, EventArgs e)
        {
             if(cust.Checked)
            {
                flight_data.Visible = false;
                customerInfo.Visible = true;
                ClearControls();

            }
            else
            {
                flight_data.Visible = false;
                customerInfo.Visible = false;
                ClearControls();
            }
        }
    }
}