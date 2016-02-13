<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:layoutTitle default="Blagger" /></title>
    <g:layoutHead />
    <r:require module="blog"/>
    <r:layoutResources/>
    <link rel="stylesheet" href="${resource(dir:'/css/',file:'autocomplete.css')}" />
     <g:javascript src="jquery-1.12.0.js"/>
     <g:javascript src="jquery-ui.js"/>

     <g:javascript>
     $(document).ready(function() {
            $("#category_textField").autocomplete({
                     source: '<g:createLink controller ='blog' action='autoList'/>',
                     minLength: 3
                });
      });

     function displayPage(requestParams){
               window.location.href="${createLink(controller:'Blog' ,action:'list' ,params:[categoryName:""])}" + requestParams;
     }
     </g:javascript>

</head>

<body>

<header class="jumbotron subhead" id="overview">
    <div class="container">
        <g:link controller="blog" action="list"><h1>Blagger</h1></g:link>
        <p class="lead">Blogging for blaggers; it's what people think you know that matters</p>
        <g:link controller="blog" action="create" class="btn btn-large btn-primary">Blag something...</g:link>
    </div>
</header>

<div class="container">
    <g:layoutBody />
</div>

<r:layoutResources/>

</body>
</html>