<div class="its-request">
 <h1> Get Configurations</h1>
  <div class="container">
    <div class="row col-md-12">
 <#if configuration?has_content>
            <table class="table table-striped table-bordered" style="background-color:white;">
            <thead>
                <tr style="background-color:#006400;color:white;">
                     <th>SMS Url</th>
                     <th>Parameter Username</th>
                     <th>Parameter Password</th>
                     <th>Send Code</th>
                     <th>Message Header</th>
                     <th>Message Footer</th>
                </tr>
            </thead>
	            <tr>
	                    <td><input type="text" onchange="changeConfiguration(this.id)" class="form-control" id="configuration-${configuration.smsUrl}" name="${configuration.smsUrl}" value="${configuration.smsUrl}"></td>
	                    <td><input type="text" onchange="changeConfiguration(this.id)" class="form-control" id="configuration-${configuration.smsUrl}" name="${configuration.parameterUsername}" value="${configuration.parameterUsername}"></td>
	                    <td><input type="text" onchange="changeConfiguration(this.id)" class="form-control" id="configuration-${configuration.smsUrl}" name="${configuration.parameterPassword}" value="${configuration.parameterPassword}"></td>
	                    <td><input type="text" onchange="changeConfiguration(this.id)" class="form-control" id="configuration-${configuration.smsUrl}" name="${configuration.sendCode}" value="${configuration.sendCode}"></td>
	                    <td><input type="text" onchange="changeConfiguration(this.id)" class="form-control" id="configuration-${configuration.smsUrl}" name="${configuration.messageHeader}" value="${configuration.messageHeader}"></td>
	                    <td><input type="text" onchange="changeConfiguration(this.id)" class="form-control" id="configuration-${configuration.smsUrl}" name="${configuration.messageFooter}" value="${configuration.messageFooter}"></td>
	                    <td><button type="submit" onclick="editConfiguration(this.id)" id="configuration-${configuration.smsUrl}" class="btn"><span class="glyphicon glyphicon-edit"></span></button>&nbsp&nbsp&nbsp<button type="submit" onclick="deleteConfiguration(this.id)" id="configuration-${configuration.smsUrl}" class="btn"><span class="glyphicon glyphicon-remove"></span></button></td>
	            </tr>
        </table>
</#if> 
</div>
</div>
</div>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
