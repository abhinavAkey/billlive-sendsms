<#if its_response_data.defaultIP??>
    <#assign ip_address=its_response_data.defaultIP>
<#else>
    <#assign ip_address="149.174.118.17">
</#if>

<div class="its-request">
    <h1>${request_type}</h1>

    <form class="pure-form pure-form-aligned" name="its-request" id="its-request-form"
          action="" method="post">
        <fieldset>
            <div class="pure-control-group">
                <label for="instance">Instance</label>
                <select id="instance" name="instance">
                    <option value=${its_response_data.itsHost}>${its_response_data.itsHost}</option>
                <#if its_response_data.itsInstances??>
                    <#list its_response_data.itsInstances?keys as key>
                        <option value="${key}">${key} ( ${its_response_data.itsInstances[key]} )
                        </option>
                    </#list>
                </#if>
                </select>
            </div>

        <#--<legend>${request_type}</legend>-->
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

            <div class="pure-control-group">
                <label>User ID</label>
                <input type="text" name="user_id" id="user_id" placeholder="user_id">
            </div>

        <#if request_type == "/check_state" ||  request_type == "/get_data">
            <div class="pure-control-group">
                <label for="ip">IP Address</label>

                <input type="text" name="ip" id="ip" value="${its_response_data.defaultIP}" required
                       onfocus="this.select();">

                <span class="pure-form-message-inline">Required</span>
            </div>

            <div class="pure-control-group">
                <label for="locale">Locale</label>
                <input type="text" name="locale" id="locale" placeholder="Locale">
            </div>
        </#if>

        <#if request_type == "/get_history">
            <div class="pure-control-group">
                <label for="locale">Locale</label>
                <input type="text" name="locale" id="locale" placeholder="Locale">
            </div>
        </#if>

        <#if request_type =="/submit_claim" >
            <div class="pure-control-group">
                <label for="context">Context</label>
                <input type="text" name="context" id="context" placeholder="context" required>
            </div>

            <div class="pure-control-group">
                <label for="ip">IP Address</label>

                <input type="text" name="ip" id="ip" value="${its_response_data.defaultIP}"
                       onfocus="this.select();">

                <span class="pure-form-message-inline">Optional</span>
            </div>

            <div class="pure-control-group">
                <label for="locale">Locale</label>
                <input type="text" name="locale" id="locale" placeholder="Locale">
            </div>

            <div class="pure-control-group">
                <label for="source">Source</label>
                <input type="text" name="source" id="source" placeholder="source">
            </div>

            <div class="pure-control-group">
                <label for="reason">Reason</label>
                <input type="text" name="reason" id="reason" placeholder="reason">
            </div>

            <div class="pure-control-group">
                <label for="xff">Xff</label>
                <input type="text" name="xff" id="xff" placeholder="xff">
            </div>

            <div class="pure-control-group">
                <label for="confidence">Confidence</label>
                <input type="text" name="confidence" id="confidence" placeholder="confidence">
            </div>

            <div class="pure-control-group">
                <label for="value">State</label>
                <input type="text" name="value" id="value" placeholder="value">
            </div>
        </#if>

        <#if request_type == "/check_userdata" >
            <div class="pure-control-group">
                <label for="ip">IP Address</label>

                <input type="text" name="ip" id="ip" required value="${its_response_data.defaultIP}"
                       onfocus="this.select();">

            </div>


            <div class="pure-control-group">
                <label for="dp">DP</label>
                <input type="text" name="dp" id="dp" placeholder="dp">
            </div>
        </#if>


        <#if request_type =="/add_credential" >
            <div class="pure-control-group">
                <label for="context">Context</label>
                <input type="text" name="context" id="context" placeholder="context" required>
            </div>

            <div class="pure-control-group">
                <label for="source">Source</label>
                <input type="text" name="source" id="source" placeholder="source">
            </div>

            <div class="pure-control-group">
                <label for="value">Value</label>
                <input type="text" name="value" id="value" placeholder="value">
            </div>

            <div class="pure-control-group">
                <label for="value1">Value1</label>
                <input type="text" name="value1" id="value1" placeholder="value1">
                <span class="pure-form-message-inline">Required only if type = asq</span>
            </div>

            <div class="pure-control-group">
                <label for="type">Type</label>
                <input type="text" name="type" id="type" placeholder="type">
            </div>

            <div class="pure-control-group">
                <label for="createTime">Create time</label>
                <input type="tel" name="createTime" id="createTime" placeholder="">
            </div>

            <div class="pure-control-group">
                <label for="verifiedTime">Verified time</label>
                <input type="tel" name="verifiedTime" id="verifiedTime" placeholder="">
            </div>

            <div class="pure-control-group">
                <label for="verified">Verified</label>
                <input type="text" name="verified" id="verified" placeholder="">
            </div>
        </#if>

        <#if request_type == "/get_user_activity">
            <div class="pure-control-group">
                <input type="text" name="context" id="context" value="useractivity" hidden required>
            </div>
        </#if>

        <#if request_type == "/verify_user">
            <div class="pure-control-group">
                <label for="ip">IP Address</label>

                <input type="text" name="ip" id="ip" value="${its_response_data.defaultIP}" required
                       onfocus="this.select();">

                <span class="pure-form-message-inline">Required</span>
            </div>


            <div class="pure-control-group">
                <label for="dp">DP</label>
                <input type="text" name="dp" id="dp" placeholder="dp">
            </div>

            <div class="pure-control-group">
                <label for="chad">Chad</label>
                <input type="text" name="chad" id="chad" placeholder="chad">
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
