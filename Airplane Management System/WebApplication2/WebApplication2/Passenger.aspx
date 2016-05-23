<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site.Master" CodeBehind="Passenger.aspx.cs" Inherits="WebApplication2.Passenger" %>

<%@ Register TagPrefix="telerik" Namespace="Telerik.Web.UI" Assembly="Telerik.Web.UI" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <br />
      <asp:Label runat="server" Font-Bold="true"> CUSTOMER INFO:</asp:Label>
    <br />
    <br />
    <br />
    <br />
    <asp:Panel ID="choice" runat="server">
        <b>SELECT ONE OF THE FOLLOWING:</b><BR />
        <asp:RadioButton ID="flight" runat="server" GroupName="grp" Text="Query based on Flight number and date" Font-Bold="false" AutoPostBack="true" OnCheckedChanged="flight_CheckedChanged"/>
        <br />
        <asp:RadioButton ID="cust" runat="server" GroupName="grp" Text="Query based on Customer info. " Font-Bold="false" AutoPostBack="true" OnCheckedChanged="cust_CheckedChanged1" />
        <br />
        <br />
        <br />
    </asp:Panel>
    <asp:Panel ID="flight_data" runat="server" Visible="false">
        <b>Flight Number:</b><asp:TextBox ID="flightnumber" runat="server" />&nbsp;<asp:Label ID="Label1" runat="server" Text=""></asp:Label>
        <br />
        <br />
        <telerik:RadDatePicker ID="RadDatePicker1" runat="server">
            <Calendar ID="Calendar1" runat="server" EnableKeyboardNavigation="true">
            </Calendar>
            <DateInput ToolTip="Date input" runat="server" Label="DATE" Font-Bold="false"></DateInput>
        </telerik:RadDatePicker>
        &nbsp;<asp:Label ID="Label2" runat="server" Text=""></asp:Label>
        <br />
        <br />

        <telerik:RadButton ID="RadButton1" Text="GO" runat="server" OnClick="RadButton1_Click">
        </telerik:RadButton>

        <br />
        <br />
        <br />
        <br />
    </asp:Panel>
    <asp:Panel ID="customerInfo" runat="server" Visible="false"> 

        <b>Customer Name:<asp:TextBox ID="custname" runat="server" /></b> &nbsp;<asp:Label ID="Label3" runat="server" Text=""></asp:Label>
        <br />
        <br />
        <telerik:RadButton ID="RadButton2" Text="GO" runat="server" OnClick="RadButton2_Click">
        </telerik:RadButton>
        <br />
        <br />
        <br />
        <br />
    </asp:Panel>
    <asp:Panel ID="datagrid" runat="server" CssClass="myclass">
        <telerik:RadGrid runat="server" ID="RadGrid2" AllowPaging="True" AllowSorting="True" BorderStyle="Solid"
            OnNeedDataSource="RadGrid2_NeedDataSource" AllowFilteringByColumn="True" Visible="False" Width="790px">
            <ClientSettings>
                <Scrolling AllowScroll="true" UseStaticHeaders="True" ScrollHeight="150" />
            </ClientSettings>

        </telerik:RadGrid>
    </asp:Panel>
</asp:Content>

