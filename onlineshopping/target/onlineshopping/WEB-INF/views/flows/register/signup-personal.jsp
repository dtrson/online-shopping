<%@include file="../shared/flow-header.jsp"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="container">

	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Sign Up - Personal</h4>
				</div>
				<div class="panel-body">

					<!-- FORM ELEMENTS -->
					<sf:form class="form-horizontal" method="POST" id="registerForm" modelAttribute="user">

						<div class="form-group">
							<label class="control-label col-md-4">First Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="firstName" placeholder="First Name"
									class="form-control" />
								<sf:errors path="firstName" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4">Last Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="lastName" placeholder="Last Name"
									class="form-control" />
								<sf:errors path="lastName" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4">Email</label>
							<div class="col-md-8">
								<sf:input type="text" path="email" placeholder="abc@zyx.com"
									class="form-control" />
								<sf:errors path="email" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4">Contact Number</label>
							<div class="col-md-8">
								<sf:input type="text" path="contactNumber"
									placeholder="Contact Number" class="form-control" />
								<sf:errors path="contactNumber" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4">Password</label>
							<div class="col-md-8">
								<sf:input type="password" path="password" placeholder="Password"
									class="form-control" />
								<sf:errors path="password" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4">Confirm Password</label>
							<div class="col-md-8">
								<sf:input type="password" path="confirmPassword"
									placeholder="Re-type password" class="form-control" />
								<sf:errors path="confirmPassword" cssClass="help-block" element="em" />
							</div>
						</div>

						<!-- radio button using bootstrap for selecting role -->
						<div class="form-group">
							<label class="control-label col-md-4">Select Role</label>
							<div class="col-md-8">
								<label class="radio-inline"><sf:radiobutton path="role" value="USER" checked="checked" /> User</label> 
								<label class="radio-inline"><sf:radiobutton path="role" value="SUPPLIER" /> Supplier</label>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<button type="submit" name="_eventId_billing" class="btn btn-primary">
									Next - Billing <i class="fa fa-chevron-right" aria-hidden="true"></i>
								</button>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>
</div>


<%@include file="../shared/flow-footer.jsp"%>