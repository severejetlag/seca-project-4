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
          <div className='form-row'>
            <div className='form-group col-md-6'>
              <label htmlFor="userName">Username</label>
              <input className='form-control' id='userName-input' name="userName" type="text" value={this.state.userInfo.userName} onChange={this.handleChange} />
            </div>
          </div>
          <div className='form-row'>
            <div className='form-group col-md-6'>
              <label htmlFor="firstName">First Name</label>
              <input className='form-control' id='firstName-input' name="firstName" type="text" value={this.state.userInfo.firstName} onChange={this.handleChange} />
            </div>
          </div>
          <div className='form-row'>
            <div className='form-group col-md-6'>
              <label htmlFor="lastName">Last Name</label>
              <input className='form-control' id='lastName-input' name="lastName" type="text" value={this.state.userInfo.lastName} onChange={this.handleChange} />
            </div>
          </div>
          <div className='form-row'>
            <div className='form-group col-md-6'>
              <label htmlFor="password">Password</label>
              <input className='form-control' id='password-input' name="password" type="password" value={this.state.userInfo.password} onChange={this.handleChange} />
            </div>
          </div>
          <div className='form-row'>
            <div className='form-group col-md-6'>
              <label htmlFor="neighborhood">Neighborhood</label>
              <input className='form-control' id='neighborhood-input' name="neighborhood" type="text" value={this.state.userInfo.neighborhood} onChange={this.handleChange} />
            </div>
          </div>
          <div className='form-row'>
            <div className='form-group col-md-6'>
              <label htmlFor="bio">Bio</label>
              <textarea className='form-control' id='bio-input' name="bio" type="text" value={this.state.userInfo.bio} onChange={this.handleChange} />
            </div>
          </div>
          <div className='form-row'>
            <div className='form-group col-md-6'>
              <input className='btn btn-primary' id='signup-submit' type='submit' value='Update'/>
            </div>
          </div>
        </form>
      </div>
    )
  }
}

export default ProfilePageEditForm
