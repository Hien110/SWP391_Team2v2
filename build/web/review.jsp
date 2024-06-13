<!DOCTYPE html>
<html>
<head>
    <title>Review payment</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css">
        table { border: 0; }
        table td { padding: 10px; }
    </style>
</head>
<body>
    <div align="center">
        <h1>Please review Before paying</h1>
        <form action="execute_payment" method="post">
            <table>
                <tr>
                    <td colspan="2"><b>Transaction Detail:</b></td>
                    <td>
                        <input type="hidden" name="paymentId" value="${param.paymentId}">
                        <input type="hidden" name="PayerID" value="${param.PayerID}">
                        <input type="hidden" name="productId" value="${param.productId}">
                        <input type="hidden" name="userId" value="${param.userId}">
                        <input type="hidden" name="quantity" value="${param.quantity}">
                        <input type="hidden" name="receiverInfoId" value="${param.receiverInfoId}">
                        <input type="hidden" name="promotionId" value="${param.promotionId}">
                    </td>
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
                    <td colspan="2"><b>Payer Information:</b></td>
                    <td></td>
                </tr>
                <tr>
                    <td>First Name:</td>
                    <td>${payer.firstName}</td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td>${payer.lastName}</td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>${payer.email}</td>
                </tr>
                <tr><td><br/></td></tr>
                <!-- Shipping Address -->
                <tr>
                    <td colspan="2"><b>Shipping Address:</b></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Recipient Name:</td>
                    <td>${shippingAddress.recipientName}</td>
                </tr>
                <tr>
                    <td>Line1:</td>
                    <td>${shippingAddress.line1}</td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td>${shippingAddress.city}</td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td>${shippingAddress.state}</td>
                </tr>
                <tr>
                    <td>Country Code:</td>
                    <td>${shippingAddress.countryCode}</td>
                </tr>
                <tr>
                    <td>Postal Code:</td>
                    <td>${shippingAddress.postalCode}</td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Pay now"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
