import React, { Component } from 'react'

class UserLoginForm extends Component {
  state = {
    userInfo: {},
    isAdmin: false
  }

  handleChange = (event) => {
    const attributeToChange = event.target.name
    const newValue = event.target.value

    const updatedUserInfo = { ...this.state.userInfo }
    updatedUserInfo[attributeToChange] = newValue
    this.setState({ userInfo: updatedUserInfo })
  }

  handleAdminToggle = (event) => {
    this.setState({
      isAdmin: !this.state.isAdmin
    })
  }

  handleSubmit = (event) => {
    event.preventDefault()
    this.props.userLogin(this.state.userInfo, this.state.isAdmin)
    this.setState({
      userInfo:{},
      isAdmin: false
    })
  }

  render() {
    return (
      <div>
        <h2>Enter Your Username To Login</h2>

        <form onSubmit={this.handleSubmit} id='login-form'>
          <div>
            <label htmlFor="userName">Username</label>
            <input
              id='userName-input'
              name="userName"
              type="text"
              onChange={this.handleChange} />
          </div>

          <label htmlFor="isAdmin">Check for admin functionality</label>
          <input type="checkbox" id="admin-checkbox" name="isAdmin"  onClick={this.handleAdminToggle}/>

          <div>
            <input id='login-submit' type='submit' value='Login'/>
          </div>
        </form>

      </div>
    )
  }
}

export default UserLoginForm
