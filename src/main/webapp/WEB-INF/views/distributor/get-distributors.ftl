<div class="its-request">
 <h1> Get Distributors</h1>
 <#list distributors as distributor>
            <ul>
                <li>Distributor Name: <strong>${distributor.distributorName}</strong></li>
                <li>Distributor Location: <strong>${distributor.distributorLocation}</strong></li>
                <li>Distributor Phone: <strong>${distributor.distributorPhone}</strong></li>
                <br>
            </ul>
</#list> 
</div>
