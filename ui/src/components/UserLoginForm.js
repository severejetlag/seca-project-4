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

        <form className='form-group' onSubmit={this.handleSubmit} id='login-form'>
          <div className="form-row">
            <div className='form-group col-md-6'>
              <label htmlFor="userName">Username</label>
              <input
                className='form-control'
                id='userName-input'
                name="userName"
                type="text"
                onChange={this.handleChange} />
              </div>
          </div>
          <div className="form-row">
            <div className="form-group col-md-6">
              <input className='form-check-input' type="checkbox" id="admin-checkbox" name="isAdmin"  onClick={this.handleAdminToggle}/>
              <label className='form-check-label' htmlFor="isAdmin">Check for admin functionality</label>
            </div>
          </div>
          <div className="form-row">
            <div className='form-group'>
              <input className='btn btn-primary' id='login-submit' type='submit' value='Login'/>
            </div>
          </div>
        </form>

      </div>
    )
  }
}

export default UserLoginForm
