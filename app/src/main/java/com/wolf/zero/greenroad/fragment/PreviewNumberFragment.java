package com.wolf.zero.greenroad.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.bean.SerializablePreview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author sineom
 * @version 1.0
 * @time 2017/7/31 22:36
 * @des ${TODO}
 * @updateAuthor ${Author}
 * @updataTIme 2017/7/31
 * @updataDes ${描述更新内容}
 */

public class PreviewNumberFragment extends Fragment {

    private static PreviewNumberFragment sFragment;
    private static SerializablePreview mPreviewDetail1;
    @BindView(R.id.text_view_preview_detail)
    TextView mTextViewPreviewDetail;

    Unbinder unbinder;
    @BindView(R.id.preview_item_img_photo)
    ImageView mPreviewItemImgPhotoNumber;

    public static PreviewNumberFragment newInstance(SerializablePreview previewDetail) {
        if (sFragment == null) {
            sFragment = new PreviewNumberFragment();
        }
        mPreviewDetail1 = previewDetail;
        return sFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preview_detail_fragment_number, container, false);
        unbinder = ButterKnife.bind(this, view);

        Bitmap bitmap = BitmapFactory.decodeFile(mPreviewDetail1.getPhotoPath1());
        if (bitmap != null) {
            mPreviewItemImgPhotoNumber.setImageBitmap(bitmap);
        }
        mTextViewPreviewDetail.setText(getString(R.string.tv_car_number));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
