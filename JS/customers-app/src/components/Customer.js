import React, { Component } from "react";
import CustomerData from "../data/customers.json";

class Customer extends Component {
  state = {
    searchString: ""
  };

  searchHandler = event => {
    this.setState({ searchString: event.target.value });
  };

  render() {
    return (
      <div className="container">
        <h1>List of Customers In The Company</h1>
        <form>
          <span className="navbar-brand mb-0 h1">
            Search Customer by First Name:
          </span>
          <input type="text" onChange={this.searchHandler} />
        </form>

        {this.state.searchString == "" ? null : (
          <table className="table table-light">
            <thead className="thead-dark">
              <tr>
                <th>Id</th>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Ip</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>Created</th>
                <th>Updated</th>
              </tr>
            </thead>
            <tbody>
              {CustomerData.map((customer, index) => {
                if (
                  customer.first_name
                    .toLowerCase()
                    .includes(this.state.searchString.toLowerCase())
                ) {
                  return (
                    <tr>
                      <td>{customer.id}</td>
                      <td>{customer.email}</td>
                      <td>{customer.first_name}</td>
                      <td>{customer.last_name}</td>
                      <td>{customer.ip}</td>
                      <td>{customer.latitude}</td>
                      <td>{customer.longitude}</td>
                      <td>{customer.created_at}</td>
                      <td>{customer.updated_at}</td>
                    </tr>
                  );
                }
              })}
            </tbody>
          </table>
        )}
      </div>
    );
  }
}
export default Customer;
