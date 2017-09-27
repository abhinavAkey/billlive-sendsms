<div class="userservice-request">
    <h1>${request_type}</h1>

    <form class="pure-form pure-form-aligned" name="userservice-request" 
            id="userservice-request-form" action="" method="post">

        <fieldset>
            <div class="pure-control-group">
                <label for="username">Username</label>
                <input type="text" name="username" id="username" placeholder="username"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>

            <div class="pure-control-group">
                <label for="pid">PID</label>
                <input type="text" name="pid" id="pid" value="aol" required>
                <span class="pure-form-message-inline">Required</span>
            </div>

            <#if request_type == "/resetPassword">
                <div class="pure-control-group">
                    <label for="pw">Password</label>
                    <input type="text" name="pw" id="pw" value="testing123" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
            </#if>

            <#if request_type == "/resetAsq">
                <div class="pure-control-group">
                    <label for="question">Question</label>
                    <input type="text" name="question" id="question" value="What was your first concert?" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
                <div class="pure-control-group">
                    <label for="answer">Answer</label>
                    <input type="text" name="answer" id="answer" value="12345" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
            </#if>

            <#if request_type == "/set2fa">
                <div class="pure-control-group">
                    <label for="phone">Phone number</label>
                    <input type="text" name="phone" id="phone" value="" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
                <div class="pure-control-group">
                    <label for="country">Country</label>
                    <input type="text" name="country" id="country" value="US" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
            </#if>
            
            <#if request_type == "/setEmailFlags">
                <div class="pure-control-group">
                    <label for="email">Email Address</label>
                    <input type="text" name="email" id="email" value="" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
                <div class="pure-control-group">
                    <label for="primary">Primary</label>
                    <input type="text" name="primary" id="email" value="true" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
                <div class="pure-control-group">
                    <label for="verified">Verified</label>
                    <input type="text" name="verified" id="verified" value="true" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
                <div class="pure-control-group">
                    <label for="lgAlt">Alt Email</label>
                    <input type="text" name="lgAlt" id="email" value="false" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
            </#if>
            
            <#if request_type == "/setAccountStatus">
                <div class="pure-control-group">
                    <label for="status">Status</label>
                    <input type="text" name="status" id="status" value="A" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
            </#if>
            
            <#if request_type == "/createUser">
                <div class="pure-control-group">
                    <label for="pw">Password</label>
                    <input type="text" name="pw" id="pw" value="testing123" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
                <div class="pure-control-group">
                    <label for="firstName">First Name</label>
                    <input type="text" name="firstName" id="firstName" value="Steve" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
                <div class="pure-control-group">
                    <label for="lastName">Last Name</label>
                    <input type="text" name="lastName" id="lastName" value="Case" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
                <div class="pure-control-group">
                    <label for="phone">Phone</label>
                    <input type="text" name="phone" id="phone" value="7032651000" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
                <div class="pure-control-group">
                    <label for="country">Country</label>
                    <input type="text" name="country" id="country" value="US" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
                <div class="pure-control-group">
                    <label for="bid">bid</label>
                    <input type="text" name="bid" id="bid" value="1" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
                <div class="pure-control-group">
                    <label for="brand">Brand</label>
                    <input type="text" name="brand" id="brand" value="100" 
                        required onfocus="this.select();">
                    <span class="pure-form-message-inline">Required</span>
                </div>
            </#if>

            <div class="pure-controls">
                <button type="reset" value="Reset" class="button-warning pure-button">Reset fields
                </button>
                 <button type="submit" class="pure-button pure-button-primary">Send request
                </button>
            </div>
        </fieldset>
    </form>
</div>
