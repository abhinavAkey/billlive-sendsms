<div class="its-request">
 <h1> Add Product</h1>
    <form class="pure-form pure-form-aligned" name="its-request" id="its-request-form"
          action="" method="post">
      <fieldset>
        <div style="width:80%; float:left;">
    	  <div class="container" style="width:160%;">
            <div class="form-row">
                <div class="col-lg-3">
                    <div class="form-group">
                    	<label for="productName" class="col-lg-12 control-label">Product Name</label>
                     	<input type="text" class="form-control" placeholder="Product Name" id="productName" name="productName" value="" required>                     	
                     </div>
                </div>
                <div class="col-lg-3">
                    <div class="form-group">
                    	  <label for="productCategory" class="col-lg-12 control-label">Product Category</label>
                     	  <input type="text" class="form-control" placeholder="Product Catogery" id="productCategory" name="productCategory" value="" required>
                     </div>
                </div>
                <div class="col-lg-3">
                    <div class="form-group">
                    <label for="brandName" class="col-lg-12 control-label">Brand Name</label>
                     <input type="text" class="form-control" placeholder="Brand Name" id="brandName" name="brandName" value="">
                     </div>
                </div>
                <div class="col-lg-3">
                        <div class="form-group">
                            <label for="hsnCode" class="col-lg-12 control-label">HSN Code</label>
                            <input type="text" class="form-control" placeholder="Enter HSN Code" id="hsnCode" name="hsnCode" value="">
                        </div>
                </div>
                 <div class="col-lg-3">
                        <div class="form-group">
                            <label for="costPriceExcludingTax" class="col-lg-12 control-label">Cost Price Excl Tax</label>
                            <input type="text" class="form-control" placeholder="0.00" id="costPriceExcludingTax" name="costPriceExcludingTax" value="">
                        </div>
                </div>
                <div class="col-lg-3">
                        <div class="form-group">
		                    <label for="quantityType" class="col-lg-12 control-label">Quantity Type</label>
		                    <select class="form-control" id="quantityType" name="quantityType" value="">
		                        <option value="KG">KG</option>
		                        <option value="GRAMS">GRAMS</option>
		                        <option value="QUINTAL">QUINTAL</option>
		                        <option value="TONNES">TONNES</option>
		                        <option value="LITERS">LITERS</option>
		                        <option value="ML">ML</option>
		                        option value="PIECES">PIECES</option>
		                    </select>
                    	</div>
                </div>
                <div class="col-lg-3">
                    <div class="form-group">
                        <label for="quantityReceived" class="col-lg-6 control-label">Quantity(Recieved)</label>
                        <input type="text" class="form-control" placeholder="0" id="quantityReceived" name="quantityReceived" value="">
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="form-group">
                        <label for="quantityAvailable" class="col-lg-12 control-label">Available Quantity</label>
                        <input type="text" class="form-control" placeholder="0" id="quantityAvailable" name="quantityAvailable" value="">
                    </div>
                 </div>
                 <div class="col-lg-3">
                        <div class="form-group">
                            <label for="taxableAmount" class="col-lg-12 control-label">Taxeble Amount</label>
                            <input type="text" class="form-control" placeholder="0.00" id="taxableAmount" name="taxableAmount" value="">
                        </div>
                </div>
                <div class="form-group col-lg-3">
                        <label for="gstTax" class="col-lg-12 control-label">GST(Tax rates)</label>
                        <select id="gstTax" class="form-control" id="gstTax" name="gstTax" value="">
                            <option value="">Select Tax</option>
                        </select>
                </div>
                <div class="col-lg-3">
                        <div class="form-group">
                            <label for="taxAmount" class="col-lg-12 control-label">Tax Amount</label>
                            <input type="text" class="form-control" placeholder="0" id="taxAmount" name="taxAmount" value="">
                        </div>
                </div>
                    <div class="col-lg-3">
                            <div class="form-group">
                                <label for="costPriceInclusiveTax" class="col-lg-12 control-label">Cost Price Incl. Tax</label>
                                <input type="text" class="form-control" placeholder="0" id="costPriceInclusiveTax" name="costPriceInclusiveTax" value="">
                            </div>
                    </div>
                    <div class="col-lg-3">
                            <div class="form-group">
                                <label for="marginInPercentage" class="col-lg-12 control-label">Margin (In %)</label>
                                <input type="text" class="form-control" placeholder="0.00" id="marginInPercentage" name="marginInPercentage" value="">
                            </div>
                    </div>
                    <div class="col-lg-3">
                            <div class="form-group">
                                <label for="marginInRupees" class="col-lg-12 control-label">Margin(In Rs)</label>
                                <input type="text" class="form-control" placeholder="0.00" id="marginInRupees" name="marginInRupees" value="">
                            </div>
                    </div>
                    <div class="col-lg-3">
                            <div class="form-group">
                                <label for="discountInPercentage" class="col-lg-12 control-label">Discount (In %)</label>
                                <input type="text" class="form-control" placeholder="0.00 %" id="discountInPercentage" name="discountInPercentage" value="">
                            </div>
                        </div>
                    <div class="col-lg-3">
                            <div class="form-group">
                                <label for="discountInRupees" class="col-lg-12 control-label">Discount (In Rs)</label>
                                <input type="text" class="form-control" placeholder="0.00 Rs" id="discountInRupees" name="discountInRupees" value="">
                            </div>
                    </div>
                <div class="col-lg-3">
                        <div class="form-group">
                            <label for="lowinvnt" class="col-lg-12 control-label">Low Inventory Level</label>
                            <input type="text" class="form-control" placeholder="0" id="lowinvnt" name="lowinvnt" value="">
                        </div>
                </div>
                <div class="col-lg-3">
                        <div class="form-group">
                            <label for="mrpPerUnit" class="col-lg-12 control-label">MRP(per Unit)</label>
                            <input type="text" class="form-control" placeholder="0" id="mrpPerUnit" name="mrpPerUnit" value="">
                        </div>
                </div>
                <div class="col-lg-3">
                        <div class="form-group">
                            <label for="discountInPercentage" class="col-lg-12 control-label">Discount on MRP(%)</label>
                            <input type="text" class="form-control" placeholder="0 %" id="discountInPercentage" name="discountInPercentage" value="">
                        </div>
                </div>
                <div class="col-lg-3">
                        <div class="form-group">
                            <label for="discountInRupees" class="col-lg-12 control-label">Discount on MRP(Rs)</label>
                            <input type="text" class="form-control" placeholder="0" id="discountInRupees" name="discountInRupees" value="">
                        </div>
                </div>
                <div class="col-lg-3">
                        <div class="form-group">
                            <label for="sellingPrice" class="col-lg-12 control-label">Selling Price(Per Unit)</label>
                            <input type="text" class="form-control" placeholder="0" id="sellingPrice" name="sellingPrice" value="">
                        </div>
                </div>
                <div class="col-lg-3">
                        <div class="form-group">
                                <label for="margin" class="col-lg-12 control-label">Margin On</label>
                                <input type="radio" name="marginOn" id="isMarginOnMrp" value="MRP" style="margin-top: -2px;" checked />
                                <label for="isMarginOnMrp"  style="margin-right: 3px;">MRP</label>
                                <input type="radio" name="marginOn" id="isMarginOnSalesPrice" value="SalesPrice" style="margin-top: -2px;" />
                                <label for="isMarginOnSalesPrice">Sales Price</label>
                        </div>
                </div>
                <div class="col-lg-3">
                        <div class="form-group">
                            <label for="expDate" class="col-lg-12 control-label">Expiry Date</label>
                            <input type="date" class="form-control" id="expDate" name="expDate" value="">
                        </div>
                </div>
                <div class="col-lg-3">
                        <div class="form-group">
                            <label for="totalUnitPrice" class="col-lg-12 control-label">Total(In Rs)</label>
                            <input type="text" class="form-control" placeholder="0" id="totalUnitPrice" name="totalUnitPrice" value="">
                        </div>
                </div>
            </div> 

            <div class="pure-controls">
                <button type="reset" value="Reset" class="button-warning pure-button">Reset fields
                </button>

                <button type="submit" class="pure-button pure-button-primary">Add Product
                </button>
            </div>
         </div>
      </div>
    </fieldset>
    </form>
</div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>