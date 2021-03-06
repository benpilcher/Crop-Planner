import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import Welcome from '../views/Welcome.vue'
import store from '../store/index'
import PlotList from '../views/PlotList.vue'
import InactivePlots from '../components/InactivePlots.vue'
import Plots from '../views/Plots.vue'
import ActivePlots from '../components/ActivePlots.vue'
import MyCrops from '../components/MyCrops.vue'
import Crop from '../components/Crop.vue'
import Notes from '../components/Notes.vue'
import CropDetails from '../views/CropDetails.vue'
import SeedList from '../views/SeedList.vue'
import Print from '../views/Print.vue'
import PlotCrop from '../components/PlotCrop.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/myplot/:plotId",
      name: "plots",
      component: Plots,
      props:(route) => {
        const plotId = Number.parseInt(route.params.plotId, 10)
        if(Number.isNaN(plotId)){
          return 0;
        }
        return plotId
      },
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/welcome",
      name: "welcome",
      component: Welcome,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/plot/:userID",
      name: "plot",
      component: PlotList,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/crop-details/:name",
      name: "crop-details",
      component: CropDetails,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/seedlist",
      name: "seed-list",
      component: SeedList,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/seedlist/print/:plotId",
      name: "print",
      component: Print,
      meta: {
        requiresAuth: false
      }
    },
    /*for testing/viewing, will be deleted*/
    {
      path: "/notes",
      name: "notes",
      component: Notes,
      meta: {
        requiresAuth: false
      }
    },    
    /*for testing/viewing, will be deleted*/
    {
      path: "/sample-plot",
      name: "sample-plot",
      component: ActivePlots, InactivePlots,
      meta: {
        requiresAuth: false
      }
    },
    {
      /*for testing/viewing, will be deleted*/
      path: "/plot-crop",
      name: "plot-crop",
      component: PlotCrop,
      meta: {
        requiresAuth: false
      }
    },
    {
      /*for testing/viewing, will be deleted*/
      path: "/mycrops",
      name: "mycrops",
      component: MyCrops,
      meta: {
        requiresAuth: false
      }
    },
    {
      /*for testing/viewing, will be deleted*/
      path: "/crop",
      name: "crop",
      component: Crop,
      meta: {
        requiresAuth: false
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/welcome");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
