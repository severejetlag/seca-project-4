import React, {Component} from 'react'

class UserSignupForm extends Component{
  state = {
    newUserInfo:{}
  }

  handleChange = (event) => {
    const attributeToChange = event.target.name
    const newValue = event.target.value

    const updatedNewUserInfo = { ...this.state.newUserInfo }
    updatedNewUserInfo[attributeToChange] = newValue
    this.setState({ newUserInfo: updatedNewUserInfo })
  }

  handleSubmit = (event) => {
    event.preventDefault()
    this.props.createUser(this.state.newUserInfo)
    this.setState({
      newUserInfo:{}
    })
  }

  render(){
    return(
      <div>
        <h2>Enter your profile information!</h2>

        <form onSubmit={this.handleSubmit} id='signup-form'>
          <div>
            <label htmlFor="userName">Username</label>
            <input id='userName-input' name="userName" type="text" onChange={this.handleChange} />
          </div>

          <div>
            <label htmlFor="firstName">First Name</label>
            <input id='firstName-input' name="firstName" type="text" onChange={this.handleChange} />
          </div>

          <div>
            <label htmlFor="lastName">Last Name</label>
            <input id='lastName-input' name="lastName" type="text" onChange={this.handleChange} />
          </div>

          <div>
            <label htmlFor="password">Password</label>
            <input id='password-input' name="password" type="password" onChange={this.handleChange} />
          </div>

          <div>
            <label htmlFor="neighborhood">Neighborhood</label>
            <input id='neighborhood-input' name="neighborhood" type="text" onChange={this.handleChange} />
          </div>

          <div>
            <label htmlFor="bio">Bio</label>
            <textarea id='bio-input' name="bio" type="text" onChange={this.handleChange} />
          </div>

          <div>
            <input id='signup-submit' type='submit' value='Signup'/>
          </div>
        </form>
      </div>
    )
  }
}

export default UserSignupForm
