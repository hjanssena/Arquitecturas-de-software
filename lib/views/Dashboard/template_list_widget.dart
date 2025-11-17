import 'package:dynadoc_front/viewmodels/template_list_view_model.dart';
import 'package:flutter/material.dart';
import 'package:pdfx/pdfx.dart';
import 'package:provider/provider.dart';

class TemplateListWidget extends StatelessWidget {
  const TemplateListWidget({super.key});

  @override
  Widget build(BuildContext context) {
    TemplateListViewModel viewModel = context.watch<TemplateListViewModel>();
    return Padding(
      padding: const EdgeInsets.only(
        left: 150,
        right: 150,
        top: 30,
        bottom: 30,
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Column(
            children: [
              Text("Plantillas", style: TextStyle(fontSize: 25)),
              Expanded(
                child: SizedBox(
                  width: 350,
                  child: GridView.count(
                    childAspectRatio: 2,
                    crossAxisCount: 2,
                    children: viewModel.getTemplateList(),
                  ),
                ),
              ),
            ],
          ),
          SizedBox(
            width: 400,
            child: Form(
              child: Column(
                children: [
                  Expanded(child: ListView(children: viewModel.getFields())),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ElevatedButton(
                      onPressed: () {},
                      child: Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Text(
                          "Generar",
                          style: TextStyle(
                            fontSize: 20,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
          Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Expanded(
                child: SizedBox(
                  width: 600,
                  child: PdfView(
                    controller: viewModel.getPdfController()!,
                    pageSnapping: true,
                    scrollDirection: Axis.vertical,
                  ),
                ),
              ),
              PdfPageNumber(
                controller: viewModel.getPdfController()!,
                // When `loadingState != PdfLoadingState.success`  `pagesCount` equals null_
                builder: (_, state, page, pagesCount) => Container(
                  alignment: Alignment.center,
                  child: Text(
                    '$page/${pagesCount ?? 0}',
                    style: const TextStyle(fontSize: 18),
                  ),
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
