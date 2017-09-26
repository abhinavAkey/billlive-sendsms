<div class="its-request">
 <h1> Add SMS Configuration</h1>
    <form class="pure-form pure-form-aligned" name="its-request" id="its-request-form"
          action="" method="post">
        <fieldset>

            <div class="pure-control-group">
                <label for="smsUrl">SMS URL</label>
                <input type="text" name="smsUrl" id="smsUrl" placeholder="SMS URL"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>

            <div class="pure-control-group">
                <label for="parameterUsername">Parameter Username</label>
                <input type="text" name="parameterUsername" id="parameterUsername" placeholder="Parameter Username"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>
            
            <div class="pure-control-group">
                <label for="parameterPassword">Parameter Password</label>
                <input type="text" name="parameterPassword" id="parameterPassword" placeholder="Parameter Password"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>
            
             <div class="pure-control-group">
                <label for="sendCode">Send Code</label>
                <input type="text" name="sendCode" id="sendCode" placeholder="Send Code"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>
            
            <div class="pure-control-group">
                <label for="messageHeader">Message Header</label>
                <input type="text" name="messageHeader" id="messageHeader" placeholder="Message Header"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>
            
            <div class="pure-control-group">
                <label for="messageFooter">Message Footer</label>
                <input type="text" name="messageFooter" id="messageFooter" placeholder="Message Footer"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>

            <div class="pure-controls">
                <button type="reset" value="Reset" class="button-warning pure-button">Reset fields
                </button>

                <button type="submit" class="pure-button pure-button-primary">Add SMS Config
                </button>
            </div>
        </fieldset>
    </form>
</div>
