import React, { Component } from "react";

class Order extends Component {
  render() {
    return (
      <div>
        <h2 className="p-3 mb-2 bg-secondary text-white">Orders</h2>

        <table className="table table-light">
          <tr>
            <th>#</th>
            <th>Date</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
          <tr>
            <td>789</td>
            <td>2018-06-15T16:00:00Z</td>
            <td>Processing</td>
            <td>
              <a href="/order/789">View</a>
            </td>
          </tr>
          <tr>
            <td>456</td>
            <td>2018-06-10T15:55:00Z</td>
            <td>Shipped</td>
            <td>
              <a href="/order/456">View</a>
            </td>
          </tr>
          <tr>
            <td>123</td>
            <td>2018-06-01T16:00:00Z</td>
            <td>Delivered</td>
            <td>
              <a href="/order/123">View</a>
            </td>
          </tr>
        </table>
      </div>
    );
  }
}
export default Order;
