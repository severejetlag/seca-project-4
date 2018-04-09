import React, {Component} from 'react'
import Nav from './Nav'
import UserLoginForm from './UserLoginForm'
import {Redirect} from 'react-router-dom'

class Login extends Component{
  render(){
    // Redirect from Stack Overflow
    //https://stackoverflow.com/questions/43230194/how-to-use-redirect-in-the-new-react-router-dom-of-reactjs
    if(this.props.hasCurrentUser){
      return <Redirect to='/userslist'/>
    }
    return(
      <main id="login-container">
        <Nav/>
        <UserLoginForm userLogin={this.props.userLogin} toggleAdminLogin={this.props.toggleAdminLogin}/>
      </main>
    )
  }
}

export default Login
