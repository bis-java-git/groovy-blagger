<html>
<body>

<g:select name="categoryName" from="${categoryList}" noSelection="['':'Choose your category']"
    onchange="displayPage(this.value);"
     class="blogselect" />

<div id="dynamic.category.update.id">
    <table border="1" class="bloghd">
    <tr>
        <thead>
            <th>Title</th>
            <th>Email</th>
            <th>Content</th>
            <th>Category</th>
        </thead>
    </tr>
    <tbody>
        <g:each in="${posts}" var="p">
            <tr>
                <div class="row">
                    <td>${p.title}</td>
                    <td>${p.email}</td>
                    <td>${p.content}</td>
                    <td>${p.category}</td>
                </div>
            </tr>
        </g:each>
    </tbody>
 </table>
 </div>

 <div class="paginateButtons">
    <g:paginate action="list" total="${total}" prev="Back" next="Forward" max="5" params="${params}" />
 </div>

</body>
</html>