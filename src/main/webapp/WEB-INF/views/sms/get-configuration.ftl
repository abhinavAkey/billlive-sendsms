<div class="its-request">
 <h1> Get Configurations</h1>
 <#if configuration?has_content>
            <table class="table table-bordered" style="background-color: #92ca7a;">
            <thead id="bgcolor">
                <tr>
                     <th>SMS Url</th>
                     <th>Parameter Username</th>
                     <th>Parameter Password</th>
                     <th>Send Code</th>
                     <th>Message Header</th>
                     <th>Message Footer</th>
                </tr>
            </thead>
	            <tr>
	                    <th><input type="text" onchange="changeConfiguration(this.id)" class="form-control" id="configuration-${configuration.smsUrl}" name="${configuration.smsUrl}" value="${configuration.smsUrl}"></th>
	                    <th><input type="text" onchange="changeConfiguration(this.id)" class="form-control" id="configuration-${configuration.smsUrl}" name="${configuration.parameterUsername}" value="${configuration.parameterUsername}"></th>
	                    <th><input type="text" onchange="changeConfiguration(this.id)" class="form-control" id="configuration-${configuration.smsUrl}" name="${configuration.parameterPassword}" value="${configuration.parameterPassword}"></th>
	                    <th><input type="text" onchange="changeConfiguration(this.id)" class="form-control" id="configuration-${configuration.smsUrl}" name="${configuration.sendCode}" value="${configuration.sendCode}"></th>
	                    <th><input type="text" onchange="changeConfiguration(this.id)" class="form-control" id="configuration-${configuration.smsUrl}" name="${configuration.messageHeader}" value="${configuration.messageHeader}"></th>
	                    <th><input type="text" onchange="changeConfiguration(this.id)" class="form-control" id="configuration-${configuration.smsUrl}" name="${configuration.messageFooter}" value="${configuration.messageFooter}"></th>
	                    <th><button type="submit" onclick="editConfiguration(this.id)" id="configuration-${configuration.smsUrl}" class="btn">Edit Configuration</button>&nbsp&nbsp&nbsp<button type="submit" onclick="deleteConfiguration(this.id)" id="configuration-${configuration.smsUrl}" class="btn">Delete Configuration</button></th>
	            </tr>
        </table>
</#if> 
</div>
