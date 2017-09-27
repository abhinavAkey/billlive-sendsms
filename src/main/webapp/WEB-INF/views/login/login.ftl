<div class="its-request">
 <h1> User Login</h1>
    <form class="pure-form pure-form-aligned" name="its-request" id="its-request-form"
          action="" method="post">
        <fieldset>

            <div class="pure-control-group">
                <label for="username">Username</label>
                <input type="text" name="username" id="username" placeholder="Username"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>

            <div class="pure-control-group">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" placeholder="Password"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>
            
            <div class="pure-controls">
                <button type="reset" value="Reset" class="button-warning pure-button">Reset fields
                </button>

                <button type="submit" class="pure-button pure-button-primary">Login
                </button>
            </div>
        </fieldset>
    </form>
</div>
