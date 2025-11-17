import 'package:dynadoc_front/viewmodels/LoginViewModel.dart';
import 'package:dynadoc_front/views/Login/LoginWidget.dart';
import 'package:dynadoc_front/views/Login/RegisterWidget.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class Loginview extends StatelessWidget {
  const Loginview({super.key});

  @override
  Widget build(BuildContext context) {
    LoginViewModel viewModel = context.watch<LoginViewModel>();
    return Scaffold(
      backgroundColor: Color.fromARGB(255, 85, 17, 136),
      body: Padding(
        padding: const EdgeInsets.all(50.0),
        child: Center(
          child: SizedBox(
            width: 500,
            height: 600,
            child: Card(
              color: Color.fromARGB(255, 255, 255, 255),
              child: Builder(
                builder: (BuildContext context) {
                  if (viewModel.isInRegistrationState()) {
                    return RegisterWidget();
                  }
                  return LoginWidget();
                },
              ),
            ),
          ),
        ),
      ),
    );
  }
}
