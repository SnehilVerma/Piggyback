import React    from "react";
import template from "./HomePage.jsx";

class HomePage extends React.Component {
  render() {
    return template.call(this);
  }
}

export default HomePage;
