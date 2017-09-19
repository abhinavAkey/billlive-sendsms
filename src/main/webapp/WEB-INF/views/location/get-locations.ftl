<div class="its-request">
 <h1> Get Locations</h1>
 <#list locations as location>
            <ul>
                <li>Location Name: <strong>${location.locationName}</strong></li>
                <li>Location City: <strong>${location.locationCity}</strong></li>
                <li>Location District: <strong>${location.locationDistrict}</strong></li>
                <li>Location State: <strong>${location.locationState}</strong></li>
                <br>
            </ul>
</#list> 
</div>
