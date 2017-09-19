<div class="its-request">
 <h1> Add Location</h1>
    <form class="pure-form pure-form-aligned" name="its-request" id="its-request-form"
          action="" method="post">
        <fieldset>

            <div class="pure-control-group">
                <label for="locationName">Location Name</label>
                <input type="text" name="locationName" id="locationName" placeholder="Location Name"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>

            <div class="pure-control-group">
                <label for="locationCity">Location City</label>
                <input type="text" name="locationCity" id="locationCity" placeholder="Location City"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>
            
            <div class="pure-control-group">
                <label for="locationDistrict">Location District</label>
                <input type="text" name="locationDistrict" id="locationDistrict" placeholder="Location District"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>
            
             <div class="pure-control-group">
                <label for="locationState">Location State</label>
                <input type="text" name="locationState" id="locationState" placeholder="Location State"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>

            <div class="pure-controls">
                <button type="reset" value="Reset" class="button-warning pure-button">Reset fields
                </button>

                <button type="submit" class="pure-button pure-button-primary">Add Location
                </button>
            </div>
        </fieldset>
    </form>
</div>
