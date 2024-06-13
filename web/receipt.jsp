<!DOCTYPE html>
<html>
    <head>
        <title>Payment receipt</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style type="text/css">
            table { border: 0; }
            table td { padding: 10px; }
        </style>
    </head>
    <body>
        <div align="center">
            <h1>Payment done! Thank you for purchasing our products.</h1>
                <table>
                    <!-- Transaction Details -->
                    <tr>
                        <td>Merchant:</td>
                        <td>Gucci</td>
                    </tr>
                    <tr>
                        <td>Payer:</td>
                        <td>${payer.firstName} ${payer.lastName}</td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td>${transaction.description}</td>
                    </tr>
                    <tr>
                        <td>Subtotal:</td>
                        <td>${transaction.amount.details.subtotal}</td>
                    </tr>
                    <tr>
                        <td>Shipping:</td>
                        <td>${transaction.amount.details.shipping}</td>
                    </tr>
                    <tr>
                        <td>Tax:</td>
                        <td>${transaction.amount.details.tax}</td>
                    </tr>
                    <tr>
                        <td>Total:</td>
                        <td>${transaction.amount.total}</td>
                    </tr>
                    <tr><td><br/></td></tr>
                    <!-- Payer Information -->
                    <tr>
                        <td colspan="2" align="center">
                            <a href="ordertracking" class="btn btn-primary">View Tracking Product</a>
                        </td>
                    </tr>
                </table>
        </div>
    </body>
</html>
