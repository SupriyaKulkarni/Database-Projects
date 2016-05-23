<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Flight.aspx.cs" MasterPageFile="~/Site.Master" Inherits="WebApplication2.Flight" %>


<%@ Register TagPrefix="telerik" Namespace="Telerik.Web.UI" Assembly="Telerik.Web.UI" %>
<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <br />
    <asp:Label runat="server" Font-Bold="true">FLIGHT INFO:</asp:Label>
    <br />
    <br />
    <br />
    <br />
    Arrival code:
    <asp:DropDownList ID="Arrivalcode" runat="server">
        <asp:ListItem Value="TPA">TPA</asp:ListItem>
        <asp:ListItem Value="HOU">HOU</asp:ListItem>
        <asp:ListItem Value="MIA">MIA</asp:ListItem>
        <asp:ListItem Value="DCA">DCA</asp:ListItem>
        <asp:ListItem Value="STL">STL</asp:ListItem>
        <asp:ListItem Value="SEA" >SEA</asp:ListItem>
        <asp:ListItem Value="SFO">SFO</asp:ListItem>
        <asp:ListItem Value="LGA">LGA</asp:ListItem>
        <asp:ListItem Value="PHX">PHX</asp:ListItem>
        <asp:ListItem Value="BWI">BWI</asp:ListItem>
        <asp:ListItem Value="PDX">PDX</asp:ListItem>
        <asp:ListItem Value="DEN">DEN</asp:ListItem>
        <asp:ListItem Value="MCI">MCI</asp:ListItem>
        <asp:ListItem Value="ORD">ORD</asp:ListItem>
        <asp:ListItem Value="CLE">CLE</asp:ListItem>
        <asp:ListItem Value="OAK">OAK</asp:ListItem>
        <asp:ListItem Value="AUS">AUS</asp:ListItem>
        <asp:ListItem Value="PHL">PHL</asp:ListItem>
        <asp:ListItem Value="LAX">LAX</asp:ListItem>
        <asp:ListItem Value="JFK">JFK</asp:ListItem>
        <asp:ListItem Value="BOS">BOS</asp:ListItem>
        <asp:ListItem Value="SAN">SAN</asp:ListItem>
        <asp:ListItem Value="ATL">ATL</asp:ListItem>
        <asp:ListItem Value="LAS">LAS</asp:ListItem>
        <asp:ListItem Value="DFW">DFW</asp:ListItem>
    </asp:DropDownList>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

    Departure Code:<asp:DropDownList ID="departurecode" runat="server">
        <asp:ListItem Value="TPA">TPA</asp:ListItem>
        <asp:ListItem Value="HOU">HOU</asp:ListItem>
        <asp:ListItem Value="MIA">MIA</asp:ListItem>
        <asp:ListItem Value="DCA">DCA</asp:ListItem>
        <asp:ListItem Value="STL">STL</asp:ListItem>
        <asp:ListItem Value="SEA" >SEA</asp:ListItem>
        <asp:ListItem Value="SFO">SFO</asp:ListItem>
        <asp:ListItem Value="LGA">LGA</asp:ListItem>
        <asp:ListItem Value="PHX">PHX</asp:ListItem>
        <asp:ListItem Value="BWI">BWI</asp:ListItem>
        <asp:ListItem Value="PDX">PDX</asp:ListItem>
        <asp:ListItem Value="DEN">DEN</asp:ListItem>
        <asp:ListItem Value="MCI">MCI</asp:ListItem>
        <asp:ListItem Value="ORD">ORD</asp:ListItem>
        <asp:ListItem Value="CLE">CLE</asp:ListItem>
        <asp:ListItem Value="OAK">OAK</asp:ListItem>
        <asp:ListItem Value="AUS">AUS</asp:ListItem>
        <asp:ListItem Value="PHL">PHL</asp:ListItem>
        <asp:ListItem Value="LAX">LAX</asp:ListItem>
        <asp:ListItem Value="JFK">JFK</asp:ListItem>
        <asp:ListItem Value="BOS">BOS</asp:ListItem>
        <asp:ListItem Value="SAN">SAN</asp:ListItem>
        <asp:ListItem Value="ATL">ATL</asp:ListItem>
        <asp:ListItem Value="LAS">LAS</asp:ListItem>
        <asp:ListItem Value="DFW">DFW</asp:ListItem>
    </asp:DropDownList>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    No.of connecting flights:<asp:DropDownList ID="numberof" runat="server">
        <asp:ListItem Value="0">No connecting flight</asp:ListItem>
        <asp:ListItem Value="1">1 Connections</asp:ListItem>
        <asp:ListItem Value="2">2 Connections</asp:ListItem>
    </asp:DropDownList>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <telerik:RadButton ID="RadButton2" Text="GO" runat="server" OnClick="RadButton2_Click" />
    <br />
    <asp:Label ID="l12" runat="server" Text=""></asp:Label>
    <br />
    <br />
    <br />
     <asp:Panel ID="datagrid" runat="server" CssClass="myclass" >
    <telerik:RadGrid runat="server" ID="RadGrid2" AllowPaging="True" AllowSorting="True" BorderStyle="Solid" 
            OnNeedDataSource="RadGrid2_NeedDataSource" AllowFilteringByColumn="True" Visible="False"  Width="845px" Height="472px">
        <ClientSettings>
            <Scrolling AllowScroll="true" UseStaticHeaders="True" ScrollHeight="150"/>
        </ClientSettings>
   
        </telerik:RadGrid>
       </asp:Panel>
</asp:Content>
