import 'package:dynadoc_front/views/Dashboard/DashboardView.dart';
import 'package:dynadoc_front/views/Login/LoginView.dart';

var appRoutes = {
  '/login': (context) => const Loginview(),
  '/dashboard': (context) => const DashboardView(),
};
