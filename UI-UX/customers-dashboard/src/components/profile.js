import React, { Component, Profiler } from "react";

class Profile extends Component {
  render() {
    return (
      <div>
        <h1 className="p-3 mb-2 bg-secondary text-white">Customer Profile</h1>

        <h2>John Doe</h2>
        <p>Email: john.doe@email.com</p>
        <p>Phone: 123-123-1234</p>
        <p>Phone (Mobile): 123-123-9876</p>
        <p>Since: March 2015</p>
        <p>Location: 162.142.45.140, 8.13333</p>
      </div>
    );
  }
}
export default Profile;
