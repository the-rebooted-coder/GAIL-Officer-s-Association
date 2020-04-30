package com.aaxena.gailofficersassociation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.util.List;

public class ExecBenefits extends Activity implements OnPageChangeListener, OnLoadCompleteListener {
    private static final String TAG = ExecBenefits.class.getSimpleName();
    public static final String BENEFIT_FILE = "benefits.pdf";
    PDFView pdfView5;
    Integer pageNumber = 0;
    String pdfFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exec_benefits);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pdfView5 = findViewById(R.id.pdfView5);
        displayFromAsset(BENEFIT_FILE);
    }

    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;

        pdfView5.fromAsset(BENEFIT_FILE)
                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView5.getDocumentMeta();
        printBookmarksTree(pdfView5.getTableOfContents(), "-");
    }
    private void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }
    public void onBackPressed() {
        finish();
    }
}