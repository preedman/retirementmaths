<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <h4>Overview</h4>
            <p>Summary of your retirement status goes here.</p>
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