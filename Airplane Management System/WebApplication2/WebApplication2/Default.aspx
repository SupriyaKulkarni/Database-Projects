<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="WebApplication2._Default" %>
<%@ Register TagPrefix="telerik" Namespace="Telerik.Web.UI" Assembly="Telerik.Web.UI" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
        <BR />
    <asp:Label runat="server" Font-Bold="true">FARE INFO:</asp:Label>
    <br />
    <br />
    <br />
    <br />
    
   <b> Flight Number:</b><asp:TextBox ID="flightnumber" runat="server"  />
    <asp:Label ID="Label1" runat="server" Text=""/>
    <asp:Button ID="subn" runat="server" OnClick="subn_Click" Text="GO" />
    <br />
    <br />
    <br />
    <br />
   <asp:Panel ID="datagrid" runat="server" CssClass="myclass" >
    <telerik:RadGrid runat="server" ID="RadGrid2" AllowPaging="True" AllowSorting="True" BorderStyle="Solid" 
            OnNeedDataSource="RadGrid2_NeedDataSource" AllowFilteringByColumn="True" Visible="False"  Width="790px">
        <ClientSettings>
            <Scrolling AllowScroll="true" UseStaticHeaders="True" ScrollHeight="150"/>
        </ClientSettings>
   
        </telerik:RadGrid>
       </asp:Panel>
</asp:Content>
