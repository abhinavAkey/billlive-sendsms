<div class="its-request">
 <h1> Add Distributor</h1>
    <form class="pure-form pure-form-aligned" name="its-request" id="its-request-form"
          action="" method="post">
        <fieldset>

            <div class="pure-control-group">
                <label for="distributorName">Distributor Name</label>
                <input type="text" name="distributorName" id="distributorName" placeholder="Distributor Name"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>
            
            <div class="pure-control-group">
                <label for="distributorLocation">Distributor Location</label>
                <select id="locationId" name="locationId" required>
                    <#list locations as location>
                        <option value="${location.locationId}">${location.locationName}
                        </option>
                    </#list>
                </select>
                <span class="pure-form-message-inline">Required</span>              
            </div>

             <div class="pure-control-group">
                <label for="distributorPhone">Distributor Phone</label>
                <input type="text" name="distributorPhone" id="distributorPhone" placeholder="Distributor Phone"
                       required>
                <span class="pure-form-message-inline">Required</span>

            </div>

            <div class="pure-controls">
                <button type="reset" value="Reset" class="button-warning pure-button">Reset fields
                </button>

                <button type="submit" class="pure-button pure-button-primary">Add Distributor
                </button>
            </div>
        </fieldset>
    </form>
</div>
