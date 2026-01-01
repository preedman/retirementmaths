<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Retirement Dashboard</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Retirement Maths</h2>
        <form action="/logout" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-outline-danger">Logout</button>
        </form>
    </div>

    <!-- Nav Tabs -->
    <ul class="nav nav-tabs" id="dashboardTabs" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link ${empty activeTab || activeTab == 'overview' ? 'active' : ''}" id="overview-tab" data-bs-toggle="tab" data-bs-target="#overview" type="button" role="tab">Drawdown</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link ${empty activeTab || activeTab == 'spending' ? 'active' : ''}" id="spending-tab" data-bs-toggle="tab" data-bs-target="#spending" type="button" role="tab">Spending</button>
            <!--
            <button class="nav-link" id="investments-tab" data-bs-toggle="tab" data-bs-target="#investments" type="button" role="tab" aria-controls="investments" aria-selected="false">Investments</button>
            -->
        </li>
        <li class="nav-item" role="presentation">
                <button class="nav-link ${activeTab == 'starting-balance' ? 'active' : ''}" id="starting-balance-tab" data-bs-toggle="tab" data-bs-target="#starting-balance" type="button" role="tab">Starting Balance</button>
            </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="settings-tab" data-bs-toggle="tab" data-bs-target="#settings" type="button" role="tab" aria-controls="settings" aria-selected="false">Documentation</button>
        </li>
    </ul>

    <!-- Tab Content -->
    <div class="tab-content p-4 border border-top-0" id="dashboardTabsContent">
        <div class="tab-pane fade ${empty activeTab || activeTab == 'overview' ? 'show active' : ''}" id="overview" role="tabpanel">
            <h4>Drawdown Time Calculator</h4>
            <p>Calculate how long your retirement savings will last.</p>

            <form:form action="/calculateDrawdown" method="post" modelAttribute="drawdownParams" class="mt-3">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label class="form-label">Starting Balance ($)</label>
                        <form:input path="startingBalance" type="number" step="0.01" class="form-control" required="required" />
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Initial Annual Withdrawal ($)</label>
                        <form:input path="initialWithdrawal" type="number" step="0.01" class="form-control" required="required" />
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Rate of Return (e.g., 0.05)</label>
                        <form:input path="realRateOfReturn" type="number" step="0.0001" class="form-control" required="required" />
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Inflation (e.g., 0.02)</label>
                        <form:input path="inflation" type="number" step="0.0001" class="form-control" required="required" />
                    </div>
                    <div class="col-12">
                        <button type="submit" class="btn btn-primary">Calculate Time</button>
                    </div>
                </div>
            </form:form>

            <c:if test="${not empty drawdownResult}">
                <div class="mt-4 alert alert-info">
                    <strong>Estimated Time until Funds Deplete:</strong> ${drawdownResult}
                </div>
            </c:if>
        </div>
        <div class="tab-pane fade ${activeTab == 'starting-balance' ? 'show active' : ''}" id="starting-balance" role="tabpanel">
                <h4>Starting Balance Calculator</h4>
                <p>Calculate the initial balance required to sustain your desired withdrawals.</p>

                <form:form action="/calculateStartingBalance" method="post" modelAttribute="startingBalanceParams" class="mt-3">
                    <div class="row g-3">
                        <div class="col-md-6">
                            <label class="form-label">Initial Annual Withdrawal ($)</label>
                            <form:input path="initialWithdrawal" type="number" step="0.01" class="form-control" required="required" />
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Desired Time (Years)</label>
                            <form:input path="desiredTimeInYears" type="number" step="1" class="form-control" required="required" />
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Real Rate of Return (e.g., 0.05)</label>
                            <form:input path="realRateOfReturn" type="number" step="0.0001" class="form-control" required="required" />
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Inflation (e.g., 0.02)</label>
                            <form:input path="inflation" type="number" step="0.0001" class="form-control" required="required" />
                        </div>
                        <div class="col-12">
                            <button type="submit" class="btn btn-primary">Calculate Starting Balance</button>
                        </div>
                    </div>
                </form:form>

                <c:if test="${not empty startingBalanceResult}">
                    <div class="mt-4 alert alert-success">
                        <strong>Required Starting Balance:</strong> $${startingBalanceResult}
                    </div>
                </c:if>
        </div>

        <div class="tab-pane fade ${activeTab == 'spending' ? 'show active' : ''}" id="spending" role="tabpanel">
            <h4>Calculate Optimal Spending</h4>
            <p>Calculate retirement spending</p>

            <form:form action="/calculateOptimalSpending" method="post" modelAttribute="optimalSpendingParams" class="mt-3">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label class="form-label">Starting Balance ($)</label>
                        <form:input path="startingBalance" type="number" step="0.01" class="form-control" required="required" />
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Subjective Discount Rate (e.g., 0.02)</label>
                        <form:input path="subjectiveDiscountRate" type="number" step="0.01" class="form-control" required="required" />
                        <br>
                        <a href="https://github.com/preedman/retirementmaths/blob/master/src/documentation/SubjectDiscountRateDefined.md" target="_blank">What is Subjective Discount Rate?</a>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Age (Years)</label>
                        <form:input path="age" type="number" step="1" class="form-control" required="required" />
                    </div>
                    <div class="col-12">
                        <button type="submit" class="btn btn-primary">Calculate Optimal Spending Rate</button>
                    </div>
                </div>
            </form:form>

            <c:if test="${not empty optimalSpendingResult}">
                <div class="mt-4 alert alert-success">
                    <strong>Optimal Spending:</strong> $${optimalSpendingResult}
                </div>
            </c:if>
        </div>


        <!--
        <div class="tab-pane fade" id="projections" role="tabpanel" aria-labelledby="projections-tab">
            <h4>Investments</h4>
            <p>Details regarding your investment portfolio.</p>
        </div>
        -->
        <div class="tab-pane fade" id="projections" role="tabpanel" aria-labelledby="projections-tab">
            <h4>Projections</h4>
            <p>Future financial growth and drawdown estimates.</p>
        </div>
        <div class="tab-pane fade" id="settings" role="tabpanel" aria-labelledby="settings-tab">
            <h4>Documentation</h4>
            <p>Worked examples of calculations and documentation</p>
            <a href="https://github.com/preedman/retirementmaths/blob/master/src/documentation/CalculateTimeForDrawdown.md" target="_blank">Calculate Time for Drawdown Example</a>
            <br>
            <a href="https://github.com/preedman/retirementmaths/blob/master/src/documentation/CalculateStartingBalance.md" target="_blank">Calculate Starting Balance Example</a>
            <br>
            <a href="https://github.com/preedman/retirementmaths/blob/master/src/documentation/CalculateYaariSpendingAustralianMortality.md" target="_blank">Calculate Optimal Spending in Australia Example</a>
            <br>
            <a href="https://github.com/preedman/retirementmaths/blob/master/src/documentation/SubjectDiscountRateDefined.md" target="_blank">What is Subjective Discount Rate?</a>
            <br>

        </div>
    </div>
</div>

<!-- Bootstrap 5 JS Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>