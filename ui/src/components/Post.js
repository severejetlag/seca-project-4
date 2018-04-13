import React from 'react'
import PostDeleteButton from './PostDeleteButton'
import PostToggleApprovalButton from './PostToggleApprovalButton'
import PostToggleVerifiedButton from './PostToggleVerifiedButton'

const Post = props => {
  return(
    <li className='post list-group-item cf' id={`post-${props.post.id}`} >
      <hgroup className='post-title-container'>
        <h2 className='cf'>
          {props.post.title}
          {
            props.post.verified?
            <small className='verified-check'> Verified Post <i class="fas fa-check"></i></small>
            :
            ''
          }
        </h2>
      </hgroup>
      <hgroup className='post-details-container'>
        <h3>{props.post.firstName} {props.post.lastName}</h3>
        <h3>{props.post.contactDetails}</h3>
      </hgroup>
      <div className="clear"></div>
      <p>{props.post.postBody}</p>
      {
        props.adminUser ?
        <div className='post-controls cf'>
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
