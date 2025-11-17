import 'package:dynadoc_front/routes.dart';
import 'package:dynadoc_front/viewmodels/LoginViewModel.dart';
import 'package:dynadoc_front/views/Login/LoginView.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [ChangeNotifierProvider(create: (_) => LoginViewModel())],
      child: MaterialApp(
        title: 'DynaDocs',
        routes: appRoutes,
        theme: ThemeData(
          colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        ),
        home: Loginview(),
      ),
    );
  }
}
