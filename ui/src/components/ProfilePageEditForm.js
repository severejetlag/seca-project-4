import React, {Component} from 'react'

class ProfilePageEditForm extends Component{
  state={
    userInfo:{
      ...this.props.currentUser
    }
  }

  handleChange = (event) => {
    const attributeToChange = event.target.name
    const newValue = event.target.value

    const updatedUserInfo = { ...this.state.userInfo }
    updatedUserInfo[attributeToChange] = newValue
    this.setState({ userInfo: updatedUserInfo })
  }

  handleSubmit = (event) => {
    event.preventDefault()
    this.props.updateUser(this.state.userInfo)
  }

  render(){
    return(
      <div>
        <h2>Update User Profile</h2>
        <form onSubmit={this.handleSubmit} id='edit-user-form'>
          <div>
            <label htmlFor="userName">Username</label>
            <input id='userName-input' name="userName" type="text" value={this.state.userInfo.userName} onChange={this.handleChange} />
          </div>

          <div>
            <label htmlFor="firstName">First Name</label>
            <input id='firstName-input' name="firstName" type="text" value={this.state.userInfo.firstName} onChange={this.handleChange} />
          </div>

          <div>
            <label htmlFor="lastName">Last Name</label>
            <input id='lastName-input' name="lastName" type="text" value={this.state.userInfo.lastName} onChange={this.handleChange} />
          </div>

          <div>
            <label htmlFor="password">Password</label>
            <input id='password-input' name="password" type="password" value={this.state.userInfo.password} onChange={this.handleChange} />
          </div>

          <div>
            <label htmlFor="neighborhood">Neighborhood</label>
            <input id='neighborhood-input' name="neighborhood" type="text" value={this.state.userInfo.neighborhood} onChange={this.handleChange} />
          </div>

          <div>
            <label htmlFor="bio">Bio</label>
            <textarea id='bio-input' name="bio" type="text" value={this.state.userInfo.bio} onChange={this.handleChange} />
          </div>

          <div>
            <input id='signup-submit' type='submit' value='Update'/>
          </div>
        </form>
      </div>
    )
  }
}

export default ProfilePageEditForm
