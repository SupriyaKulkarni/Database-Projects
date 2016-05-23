<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Avail.aspx.cs" Inherits="WebApplication2.Avail" MasterPageFile="~/Site.Master" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <br />
  <asp:Label runat="server" Font-Bold="true"> FLIGHT AVALIABILITY:</asp:Label>
    <br />
    <br />
    <br />
    <br />
    
   <b> Flight Number:</b><asp:TextBox ID="flightnumber" runat="server"  />
   
    <asp:Button ID="subn" runat="server" OnClick="subn_Click" Text="GO" />
    <br />
    <br />
    <br />
     <asp:Label ID="Label1" runat="server" Text=""/>
    </asp:Content>
