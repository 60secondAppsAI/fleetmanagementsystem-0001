import { createRouter, createWebHashHistory } from 'vue-router';
import { useAuth } from '../stores/auth';
import SignIn from '../components/SignIn.vue';
import Settings from '../components/Settings.vue';
import MyDashboard from '../components/MyDashboard.vue';

// Dynamic imports for module components
import Vehicles from '../components/Vehicles.vue';
import VehicleDetail from '../components/VehicleDetail.vue';
import Drivers from '../components/Drivers.vue';
import DriverDetail from '../components/DriverDetail.vue';
import Journeys from '../components/Journeys.vue';
import JourneyDetail from '../components/JourneyDetail.vue';
import Estimates from '../components/Estimates.vue';
import EstimateDetail from '../components/EstimateDetail.vue';
import Bookings from '../components/Bookings.vue';
import BookingDetail from '../components/BookingDetail.vue';
import Dutys from '../components/Dutys.vue';
import DutyDetail from '../components/DutyDetail.vue';
import DriverDutys from '../components/DriverDutys.vue';
import DriverDutyDetail from '../components/DriverDutyDetail.vue';
import VehicleDutys from '../components/VehicleDutys.vue';
import VehicleDutyDetail from '../components/VehicleDutyDetail.vue';
import Transactions from '../components/Transactions.vue';
import TransactionDetail from '../components/TransactionDetail.vue';
import Facilitys from '../components/Facilitys.vue';
import FacilityDetail from '../components/FacilityDetail.vue';
import Feedbacks from '../components/Feedbacks.vue';
import FeedbackDetail from '../components/FeedbackDetail.vue';
import Submitters from '../components/Submitters.vue';
import SubmitterDetail from '../components/SubmitterDetail.vue';
import Executors from '../components/Executors.vue';
import ExecutorDetail from '../components/ExecutorDetail.vue';
import Verifiers from '../components/Verifiers.vue';
import VerifierDetail from '../components/VerifierDetail.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/dashboard'
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: MyDashboard
  },
  
  // Dynamic module routes
  {
    path: '/vehicles',
    name: 'Vehicles',
    component: Vehicles
  },
  {
    path: '/vehicle/:id',
    name: 'VehicleDetail',
    component: VehicleDetail,
    props: true
  },
  {
    path: '/drivers',
    name: 'Drivers',
    component: Drivers
  },
  {
    path: '/driver/:id',
    name: 'DriverDetail',
    component: DriverDetail,
    props: true
  },
  {
    path: '/journeys',
    name: 'Journeys',
    component: Journeys
  },
  {
    path: '/journey/:id',
    name: 'JourneyDetail',
    component: JourneyDetail,
    props: true
  },
  {
    path: '/estimates',
    name: 'Estimates',
    component: Estimates
  },
  {
    path: '/estimate/:id',
    name: 'EstimateDetail',
    component: EstimateDetail,
    props: true
  },
  {
    path: '/bookings',
    name: 'Bookings',
    component: Bookings
  },
  {
    path: '/booking/:id',
    name: 'BookingDetail',
    component: BookingDetail,
    props: true
  },
  {
    path: '/dutys',
    name: 'Dutys',
    component: Dutys
  },
  {
    path: '/duty/:id',
    name: 'DutyDetail',
    component: DutyDetail,
    props: true
  },
  {
    path: '/driverDutys',
    name: 'DriverDutys',
    component: DriverDutys
  },
  {
    path: '/driverDuty/:id',
    name: 'DriverDutyDetail',
    component: DriverDutyDetail,
    props: true
  },
  {
    path: '/vehicleDutys',
    name: 'VehicleDutys',
    component: VehicleDutys
  },
  {
    path: '/vehicleDuty/:id',
    name: 'VehicleDutyDetail',
    component: VehicleDutyDetail,
    props: true
  },
  {
    path: '/transactions',
    name: 'Transactions',
    component: Transactions
  },
  {
    path: '/transaction/:id',
    name: 'TransactionDetail',
    component: TransactionDetail,
    props: true
  },
  {
    path: '/facilitys',
    name: 'Facilitys',
    component: Facilitys
  },
  {
    path: '/facility/:id',
    name: 'FacilityDetail',
    component: FacilityDetail,
    props: true
  },
  {
    path: '/feedbacks',
    name: 'Feedbacks',
    component: Feedbacks
  },
  {
    path: '/feedback/:id',
    name: 'FeedbackDetail',
    component: FeedbackDetail,
    props: true
  },
  {
    path: '/submitters',
    name: 'Submitters',
    component: Submitters
  },
  {
    path: '/submitter/:id',
    name: 'SubmitterDetail',
    component: SubmitterDetail,
    props: true
  },
  {
    path: '/executors',
    name: 'Executors',
    component: Executors
  },
  {
    path: '/executor/:id',
    name: 'ExecutorDetail',
    component: ExecutorDetail,
    props: true
  },
  {
    path: '/verifiers',
    name: 'Verifiers',
    component: Verifiers
  },
  {
    path: '/verifier/:id',
    name: 'VerifierDetail',
    component: VerifierDetail,
    props: true
  },

  // Core application routes
  {
    path: '/signin',
    name: 'SignIn',
    component: SignIn
  },
//  {
//    path: '/users',
//    name: 'Users',
//    component: Users,
//    meta: { requiresAuth: true }
//  },
  {
    path: '/settings',
    name: 'Settings',
    component: Settings,
    meta: { requiresAuth: true }
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/dashboard'
  }
];

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes,
  scrollBehavior(to, from, savedPosition) {
    return savedPosition || { top: 0 };
  }
});

// Navigation guard for authentication
router.beforeEach((to, from, next) => {
  const auth = useAuth();
  
  if (to.meta.requiresAuth && !auth.isAuthenticated) {
    next({ name: 'SignIn', query: { redirect: to.fullPath } });
  } else if (to.name === 'SignIn' && auth.isAuthenticated) {
    next({ name: 'Dashboard' });
  } else {
    next();
  }
});

export default router;
