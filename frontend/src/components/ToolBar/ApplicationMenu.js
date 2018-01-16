import React, { PureComponent } from 'react'
import PropTypes from 'prop-types'

import { Menu, Dimmer, Grid } from 'semantic-ui-react'

import ApplicationMenuItem from '../../elements/ToolBar/ApplicationMenuItem'

class ApplicationMenu extends PureComponent {
  static propTypes = {
    isAppMenuActive: PropTypes.bool.isRequired,
    toggleAppMenu: PropTypes.func.isRequired
  }

  render() {
    const { isAppMenuActive, toggleAppMenu } = this.props

    return (
      <Menu.Menu>
        <Menu.Item icon='grid layout' onClick={toggleAppMenu}/>
        <Dimmer
          active={isAppMenuActive}
          onClickOutside={toggleAppMenu}
          page>
          <Grid columns={6}>
            <Grid.Column>
              <ApplicationMenuItem icon='server' name='Nodes' toggleAppMenu={toggleAppMenu}/>
            </Grid.Column>
            <Grid.Column>
              <ApplicationMenuItem icon='wifi' name='Network' toggleAppMenu={toggleAppMenu}/>
            </Grid.Column>
            <Grid.Column>
              <ApplicationMenuItem icon='setting' name='Settings' toggleAppMenu={toggleAppMenu}/>
            </Grid.Column>
            <Grid.Column>
              <ApplicationMenuItem icon='book' name='Logs' toggleAppMenu={toggleAppMenu}/>
            </Grid.Column>
          </Grid>
        </Dimmer>
      </Menu.Menu>
    )
  }
}

export default ApplicationMenu
