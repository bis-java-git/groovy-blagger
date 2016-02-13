<html>
<r:script>
    $('input[name=title]').focus();
</r:script>

<head></head>

<body>
    <g:form controller="blog" action="createPost" class="form-horizontal">
        <fieldset>
           <legend>Blag about something new!</legend>
            <div class="control-group">
                <label class="control-label" for="title">Title</label>
                <div class="controls">
                    <g:textField name="title" value="${post?.title}" placeholder="My first blag post..." class="input-block-level"/>
                      <g:renderErrors bean="${post}" as="list" field="title" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="title">Your email</label>
                <div class="controls">
                    <g:textField name="email" value="${post?.email}" placeholder="test@test.com" class="input-block-level"/>
                   <g:renderErrors bean="${post}" as="list" field="email" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="content">The blag</label>
                <div class="controls">
                    <g:textArea name="content" value="${post?.content}" placeholder="The blag..." class="input-block-level" rows="10"/>
                    <g:renderErrors bean="${post}" as="list" field="content" />
                </div>
            </div>
            <div class="control-group">
	    	    <div class="controls">
	    	        <g:textField name="category" id="category_textField" value="${post?.category}" placeholder="The blagger category" class="input-block-level"  />
	            </div>
            </div>
            <div class="control-group">
	     	    <div class="controls">
	        	    <div>
	                	<button type="submit" class="btn">Submit</button>
	                </div>
	            </div>
            </div>
        </fieldset>
    </g:form>
</body>
</html>