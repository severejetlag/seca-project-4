import React, {Component} from 'react'

class PostNewForm extends Component{
  state = {
    newPost:{}
  }
  handleChange = (event) => {
    const attributeToChange = event.target.name
    const newValue = event.target.value

    const updatedNewPost = { ...this.state.newPost }
    updatedNewPost[attributeToChange] = newValue
    this.setState({ newPost: updatedNewPost })
  }

  handleSubmit = (event) => {
    event.preventDefault()
    this.props.createPost(this.state.newPost)
    this.setState({newPost:{}})
  }
  render(){
    return(
      <div>
        <h2>Create New Post!</h2>
        <form onSubmit={this.handleSubmit} id='post-create-form'>
          <div className='form-row'>
            <div className='form-group col-md-4'>
              <label htmlFor="title">Post Title</label>
              <input className='form-control' id='title-input' name="title" type="text" onChange={this.handleChange} />
            </div>
            <div className='form-group col-md-4'>
              <label htmlFor="firstName">First Name</label>
              {
                this.props.hasCurrentUser ?
                <input readOnly className='form-control-plaintext' id='firstName-input' name="firstName" type="text" onChange={this.handleChange} value={this.props.currentUser.firstName}/>
                :
                <input className='form-control' id='firstName-input' name="firstName" type="text" onChange={this.handleChange} />
              }
            </div>
            <div className='form-group col-md-4'>
              <label htmlFor="lastName">Last Name</label>
              {
                this.props.hasCurrentUser ?
                <input readOnly className='form-control-plaintext' id='lastName-input' name="lastName" type="text" onChange={this.handleChange} value={this.props.currentUser.lastName}/>
                :
                <input className='form-control' id='lastName-input' name="lastName" type="text" onChange={this.handleChange} />
              }
            </div>
          </div>

          <div className='form-row'>
            <div className='form-group col-md-12'>
              <label htmlFor="contactDetails">Contact Details</label>
              <input className='form-control' id='contactDetails-input' name="contactDetails" type="text" onChange={this.handleChange} />
            </div>
          </div>

          <div className='form-row'>
            <div className='form-group col-md-12'>
              <label htmlFor="postBody">Post Contents</label>
              <textarea className='form-control' id='postBody-input' name="postBody" type="text" onChange={this.handleChange} />
            </div>
          </div>

          <div className='form-row'>
            <div className='form-group col-md-12'>
              <input className='btn btn-primary' id='post-create-submit' type='submit' value='Submit'/>
            </div>
          </div>
        </form>
      </div>
    )
  }
}

export default PostNewForm
