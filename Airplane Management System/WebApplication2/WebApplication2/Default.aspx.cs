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
    public partial class _Default : Page
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
            string query = "SELECT * FROM Fare where FLIGHT_NUMBER='"+ fnum+"'" ;
      
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

        protected void subn_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(flightnumber.Text))
            {
                Label1.Text = "Please enter flight number";
                RadGrid2.Visible = false;
            }
            else
            {
                RadGrid2.Visible = true;
                RadGrid2.DataSource = GetDataTable();
                RadGrid2.Rebind();
            }
        }
    }
}