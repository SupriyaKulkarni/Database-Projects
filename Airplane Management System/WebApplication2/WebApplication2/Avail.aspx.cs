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
    public partial class Avail : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void subn_Click(object sender, EventArgs e)
        {
            string fnum = flightnumber.Text;
            int total;
            int num1;
            bool res = int.TryParse(fnum, out num1);
            if (res == false)
            {
                fnum = "";
            }
            if (string.IsNullOrEmpty(fnum))
            {
                Label1.Text = "Flight Number entered is invalid";
            }
           
            else
            {
                String ConnString = ConfigurationManager.ConnectionStrings["AMP3ConnectionString"].ToString();
                SqlConnection conn = new SqlConnection(ConnString);

                SqlCommand cmd = new SqlCommand("select count(*) from SEAT_RESERVATION where FLIGHT_NUMBER='" + fnum + "'", conn);
                conn.Open();
                object test = cmd.ExecuteScalar();
                if (test==null)
                {
                    total = 0;
                }
                else
                {
                     total = (int)(cmd.ExecuteScalar());
                }
                cmd.CommandText = "select NUMBER_OF_AVAILABLE_SEATS  from FLIGHT_INSTANCE F where FLIGHT_NUMBER='" + fnum + "'";
                object test1 = cmd.ExecuteScalar();
                if (test1==null)
                {
                    Label1.Text = "No flight with the entered number is scheduled/exists.";
                    conn.Close();
                }
                else
                {
                    int seats = (int)(cmd.ExecuteScalar());
                    int result = seats - total;
                    Label1.Text = "Avaliable seats are: " + result.ToString();
                    conn.Close();
                }

            }
        }
    }
}