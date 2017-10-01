<div class="its-request">
 <h1> Get Configurations</h1><br>
  <div class="container">
    <div class="row col-md-12">
 <#if configuration?has_content>
            <table class="table table-bordered" style="width: 85%; margin-left: 10%;">
            <thead>
                <tr >
                     <th>SMS Url</th>
                     <th>Parameter Username</th>
                     <th>Parameter Password</th>
                     <th>Send Code</th>
                     <th>Message Header</th>
                     <th>Message Footer</th>
                     <th></th>
                </tr>
            </thead>
	            <tr>
	                    <td><input type="text"  class="form-control" id="smsUrl-${configuration.configurationId}-" name="${configuration.smsUrl}" value="${configuration.smsUrl}" required></td>
	                    <td><input type="text"  class="form-control" id="user-${configuration.configurationId}-" name="${configuration.parameterUsername}" value="${configuration.parameterUsername}" required></td>
	                    <td><input type="text"  class="form-control" id="pass-${configuration.configurationId}-" name="${configuration.parameterPassword}" value="${configuration.parameterPassword}" required></td>
	                    <td><input type="text"  class="form-control" id="code-${configuration.configurationId}-" name="${configuration.sendCode}" value="${configuration.sendCode}" required></td>
	                    <td><input type="text"  class="form-control" id="header-${configuration.configurationId}-" name="${configuration.messageHeader}" value="${configuration.messageHeader}" required></td>
	                    <td><input type="text"  class="form-control" id="footer-${configuration.configurationId}-" name="${configuration.messageFooter}" value="${configuration.messageFooter}" required></td>
	                    <td><button type="submit" onclick="editConfiguration(this.id)" id="configuration-${configuration.configurationId}" name="${configuration.configurationId}" class="btn"><span class="glyphicon glyphicon-edit"></span></button></td>
	            </tr>
        </table>
</#if> 
</div>
</div>
</div>