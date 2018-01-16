import React from 'react'
import PropTypes from 'prop-types'
import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'

import { Menu } from 'semantic-ui-react'

import ApplicationMenu from '../components/ToolBar/ApplicationMenu'
import TaskBar from '../components/ToolBar/TaskBar'
import SearchApps from '../components/ToolBar/SearchApps'
import ToggleExpose from '../components/ToolBar/ToggleExpose'
import ToggleFullscreen from '../components/ToolBar/ToggleFullscreen'
import UserMenu from '../components/ToolBar/UserMenu'

import * as ToolBarActions from '../actions/ToolBarActions'

import '../assets/appmenu.css'

const ToolBar = ({isAppMenuActive, actions}) => (
  <Menu fixed='top' className='toolbar'>
    <ApplicationMenu isAppMenuActive={isAppMenuActive} toggleAppMenu={actions.toggleAppMenu}/>
    <div className='brand item'>Horizon</div>
    <TaskBar/>
    <Menu.Menu position='right'>
      <ToggleExpose/>
      <SearchApps/>
      <ToggleFullscreen/>
      <UserMenu/>
    </Menu.Menu>
  </Menu>
)

ToolBar.propTypes = {
  isAppMenuActive: PropTypes.bool.isRequired,
  actions: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
  isAppMenuActive: state.toolbar.isAppMenuActive
})

const mapDispatchToProps = dispatch => ({
  actions: bindActionCreators(ToolBarActions, dispatch)
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ToolBar)
