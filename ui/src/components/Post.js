import React from 'react'
import PostDeleteButton from './PostDeleteButton'
import PostToggleApprovalButton from './PostToggleApprovalButton'
import PostToggleVerifiedButton from './PostToggleVerifiedButton'

const Post = props => {
  return(
    <li className='post list-group-item' id={`post-${props.post.id}`} >
      <hgroup>
        <h2>{props.post.title}</h2>
        <h3>{props.post.firstName} {props.post.lastName}</h3>
        <h3>{props.post.contactDetails}</h3>
      </hgroup>
      <p>{props.post.postBody}</p>
      {
        props.adminUser ?
        <div>
          <PostToggleApprovalButton id={props.post.id} index={props.index} post={props.post} toggleApproval={props.toggleApproval}/>
          <PostToggleVerifiedButton id={props.post.id} index={props.index} post={props.post} toggleVerified={props.toggleVerified}/>
          <PostDeleteButton id={props.post.id} index={props.index} deletePost={props.deletePost}/>
        </div>
        : ""
      }
    </li>
  )
}

export default Post
