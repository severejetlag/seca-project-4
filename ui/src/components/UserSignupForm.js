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
          <div className="form-row">
            <div className='form-group col-md-6'>
              <label htmlFor="userName">Username</label>
              <input className='form-control' id='userName-input' name="userName" type="text" onChange={this.handleChange} />
            </div>
          </div>


          <div className="form-row">
            <div className='form-group col-md-6'>
              <label htmlFor="firstName">First Name</label>
              <input className='form-control' id='firstName-input' name="firstName" type="text" onChange={this.handleChange} />
            </div>
          </div>

          <div className='form-row'>
            <div className="form-group col-md-6">
              <label htmlFor="lastName">Last Name</label>
              <input className='form-control' id='lastName-input' name="lastName" type="text" onChange={this.handleChange} />
            </div>
          </div>

          <div className='form-row'>
            <div className='form-group col-md-6'>
              <label htmlFor="password">Password</label>
              <input className='form-control' id='password-input' name="password" type="password" onChange={this.handleChange} />
            </div>
          </div>

          <div className='form-row'>
            <div className="form-group col-md-6">
              <label htmlFor="neighborhood">Neighborhood</label>
              <input className='form-control' id='neighborhood-input' name="neighborhood" type="text" onChange={this.handleChange} />
            </div>
          </div>

          <div className='form-row'>
            <div className="form-group col-md-6">
              <label htmlFor="bio">Bio</label>
              <textarea className='form-control' id='bio-input' name="bio" type="text" onChange={this.handleChange} />
            </div>
          </div>

          <div className='form-row'>
            <div className="form-group col-md-6">
              <input className='btn btn-primary' id='signup-submit' type='submit' value='Signup'/>
            </div>
          </div>
        </form>
      </div>
    )
  }
}

export default UserSignupForm
