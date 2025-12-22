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
    <h2 class="mb-4">Retirement Planning Dashboard</h2>

    <!-- Nav Tabs -->
    <ul class="nav nav-tabs" id="dashboardTabs" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="overview-tab" data-bs-toggle="tab" data-bs-target="#overview" type="button" role="tab" aria-controls="overview" aria-selected="true">Overview</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="investments-tab" data-bs-toggle="tab" data-bs-target="#investments" type="button" role="tab" aria-controls="investments" aria-selected="false">Investments</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="projections-tab" data-bs-toggle="tab" data-bs-target="#projections" type="button" role="tab" aria-controls="projections" aria-selected="false">Projections</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="settings-tab" data-bs-toggle="tab" data-bs-target="#settings" type="button" role="tab" aria-controls="settings" aria-selected="false">Settings</button>
        </li>
    </ul>

    <!-- Tab Content -->
    <div class="tab-content p-4 border border-top-0" id="dashboardTabsContent">
        <div class="tab-pane fade show active" id="overview" role="tabpanel" aria-labelledby="overview-tab">
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
                        <label class="form-label">Real Rate of Return (e.g., 0.05)</label>
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
        <div class="tab-pane fade" id="investments" role="tabpanel" aria-labelledby="investments-tab">
            <h4>Investments</h4>
            <p>Details regarding your investment portfolio.</p>
        </div>
        <div class="tab-pane fade" id="projections" role="tabpanel" aria-labelledby="projections-tab">
            <h4>Projections</h4>
            <p>Future financial growth and drawdown estimates.</p>
        </div>
        <div class="tab-pane fade" id="settings" role="tabpanel" aria-labelledby="settings-tab">
            <h4>Settings</h4>
            <p>Configure your retirement parameters.</p>
        </div>
    </div>
</div>

<!-- Bootstrap 5 JS Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>