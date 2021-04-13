//package edu.wgu.c196_coursetracker_mwilliams.UI.Callbacks;
//
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.graphics.drawable.Drawable;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.RecyclerView;
//
//import edu.wgu.c196_coursetracker_mwilliams.R;
//import edu.wgu.c196_coursetracker_mwilliams.UI.Adapters.TermAdapter;
//
//public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
//
//        private Drawable icon;
//        private final ColorDrawable background;
//        TermAdapter adapter;
//
//    /**
//     * Creates a Callback for the given drag and swipe allowance. These values serve as
//     * defaults
//     * and if you want to customize behavior per ViewHolder, you can override
//     * {@link #getSwipeDirs(RecyclerView, ViewHolder)}
//     * and / or {@link #getDragDirs(RecyclerView, ViewHolder)}.
//     *
//     * @param dragDirs  Binary OR of direction flags in which the Views can be dragged. Must be
//     *                  composed of {@link #LEFT}, {@link #RIGHT}, {@link #START}, {@link
//     *                  #END},
//     *                  {@link #UP} and {@link #DOWN}.
//     * @param swipeDirs Binary OR of direction flags in which the Views can be swiped. Must be
//     *                  composed of {@link #LEFT}, {@link #RIGHT}, {@link #START}, {@link
//     *                  #END},
//     *                  {@link #UP} and {@link #DOWN}.
//     */
//    public SwipeToDeleteCallback(TermAdapter adapter) {
//        super(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
//        this.adapter = adapter;
//
//
//        icon = ContextCompat.getDrawable(adapter.getContext(),R.drawable.ic_baseline_delete_36);
//        background = new ColorDrawable(Color.RED);
//
//    }
//
//    @Override
//    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//        return false;
//    }
//
//    @Override
//    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//        int position = viewHolder.getAdapterPosition();
//        adapter.deleteItem(position);
//    }
//
//    @Override
//    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//
//        View itemView = viewHolder.itemView;
//        int backgroundCornerOffset = 20;
//
////        Background
//
//        if (dX > 0) { // Swiping to the right
//            background.setBounds(itemView.getLeft(), itemView.getTop(),
//                    itemView.getLeft() + ((int) dX) + backgroundCornerOffset,
//                    itemView.getBottom());
//
//        } else if (dX < 0) { // Swiping to the left
//            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
//                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
//        } else { // view is unSwiped
//            background.setBounds(0, 0, 0, 0);
//        }
//        background.draw(c);
//
////        Icons
//
//        int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
//        int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
//        int iconBottom = iconTop + icon.getIntrinsicHeight();
//
//        if (dX > 0) { // Swiping to the right
//            int iconLeft = itemView.getLeft() + iconMargin + icon.getIntrinsicWidth();
//            int iconRight = itemView.getLeft() + iconMargin;
//            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
//
//            background.setBounds(itemView.getLeft(), itemView.getTop(),
//                    itemView.getLeft() + ((int) dX) + backgroundCornerOffset,
//                    itemView.getBottom());
//        } else if (dX < 0) { // Swiping to the left
//            int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
//            int iconRight = itemView.getRight() - iconMargin;
//            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
//
//            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
//                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
//        } else { // view is unSwiped
//            background.setBounds(0, 0, 0, 0);
//        }
//
//        background.draw(c);
//        icon.draw(c);
//
//    }
//
//
//}
