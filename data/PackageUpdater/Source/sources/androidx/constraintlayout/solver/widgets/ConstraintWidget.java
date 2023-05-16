package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.Cache;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.analyzer.ChainRun;
import androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun;
import androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ConstraintWidget {
    public static final int ANCHOR_BASELINE = 4;
    public static final int ANCHOR_BOTTOM = 3;
    public static final int ANCHOR_LEFT = 0;
    public static final int ANCHOR_RIGHT = 1;
    public static final int ANCHOR_TOP = 2;
    private static final boolean AUTOTAG_CENTER = false;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    static final int DIMENSION_HORIZONTAL = 0;
    static final int DIMENSION_VERTICAL = 1;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    private static final boolean USE_WRAP_DIMENSION_FOR_SPREAD = false;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    private boolean hasBaseline;
    public ChainRun horizontalChainRun;
    public HorizontalWidgetRun horizontalRun;
    private boolean inPlaceholder;
    public boolean[] isTerminalWidget;
    protected ArrayList<ConstraintAnchor> mAnchors;
    ConstraintAnchor mBaseline;
    int mBaselineDistance;
    public ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    private float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    public float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    boolean mGroupsToSolver;
    int mHeight;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    boolean mHorizontalWrapVisited;
    private boolean mInVirtuaLayout;
    public boolean mIsHeightWrapContent;
    private boolean[] mIsInBarrier;
    public boolean mIsWidthWrapContent;
    public ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    public ConstraintAnchor[] mListAnchors;
    public DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    public int mMatchConstraintDefaultHeight;
    public int mMatchConstraintDefaultWidth;
    public int mMatchConstraintMaxHeight;
    public int mMatchConstraintMaxWidth;
    public int mMatchConstraintMinHeight;
    public int mMatchConstraintMinWidth;
    public float mMatchConstraintPercentHeight;
    public float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    protected int mMinHeight;
    protected int mMinWidth;
    protected ConstraintWidget[] mNextChainWidget;
    protected int mOffsetX;
    protected int mOffsetY;
    boolean mOptimizerMeasurable;
    public ConstraintWidget mParent;
    int mRelX;
    int mRelY;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    boolean mResolvedHasRatio;
    public int[] mResolvedMatchConstraintDefault;
    public ConstraintAnchor mRight;
    boolean mRightHasCentered;
    public ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    public float[] mWeight;
    int mWidth;

    /* renamed from: mX */
    protected int f35mX;

    /* renamed from: mY */
    protected int f36mY;
    public boolean measured;
    public WidgetRun[] run;
    public ChainRun verticalChainRun;
    public VerticalWidgetRun verticalRun;
    public int[] wrapMeasure;

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public WidgetRun getRun(int i) {
        if (i == 0) {
            return this.horizontalRun;
        }
        if (i == 1) {
            return this.verticalRun;
        }
        return null;
    }

    public boolean isInVirtualLayout() {
        return this.mInVirtuaLayout;
    }

    public void setInVirtualLayout(boolean z) {
        this.mInVirtuaLayout = z;
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public void setMaxWidth(int i) {
        this.mMaxDimension[0] = i;
    }

    public void setMaxHeight(int i) {
        this.mMaxDimension[1] = i;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public void setHasBaseline(boolean z) {
        this.hasBaseline = z;
    }

    public boolean getHasBaseline() {
        return this.hasBaseline;
    }

    public boolean isInPlaceholder() {
        return this.inPlaceholder;
    }

    public void setInPlaceholder(boolean z) {
        this.inPlaceholder = z;
    }

    /* access modifiers changed from: protected */
    public void setInBarrier(int i, boolean z) {
        this.mIsInBarrier[i] = z;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.f35mX = 0;
        this.f36mY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        float[] fArr = this.mWeight;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] iArr = this.mMaxDimension;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedHasRatio = false;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mOptimizerMeasurable = false;
        this.mGroupsToSolver = false;
        boolean[] zArr = this.isTerminalWidget;
        zArr[0] = true;
        zArr[1] = true;
        this.mInVirtuaLayout = false;
        boolean[] zArr2 = this.mIsInBarrier;
        zArr2[0] = false;
        zArr2[1] = false;
    }

    public ConstraintWidget() {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = new HorizontalWidgetRun(this);
        this.verticalRun = new VerticalWidgetRun(this);
        this.isTerminalWidget = new boolean[]{true, true};
        this.wrapMeasure = new int[]{0, 0, 0, 0};
        this.mResolvedHasRatio = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtuaLayout = false;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.f35mX = 0;
        this.f36mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mOptimizerMeasurable = false;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        addAnchors();
    }

    public ConstraintWidget(int i, int i2, int i3, int i4) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = new HorizontalWidgetRun(this);
        this.verticalRun = new VerticalWidgetRun(this);
        this.isTerminalWidget = new boolean[]{true, true};
        this.wrapMeasure = new int[]{0, 0, 0, 0};
        this.mResolvedHasRatio = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtuaLayout = false;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.f35mX = 0;
        this.f36mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mOptimizerMeasurable = false;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.f35mX = i;
        this.f36mY = i2;
        this.mWidth = i3;
        this.mHeight = i4;
        addAnchors();
    }

    public ConstraintWidget(int i, int i2) {
        this(0, 0, i, i2);
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public void setWidthWrapContent(boolean z) {
        this.mIsWidthWrapContent = z;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public void setHeightWrapContent(boolean z) {
        this.mIsHeightWrapContent = z;
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f, int i) {
        immediateConnect(ConstraintAnchor.Type.CENTER, constraintWidget, ConstraintAnchor.Type.CENTER, i, 0);
        this.mCircleConstraintAngle = f;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setVisibility(int i) {
        this.mVisibility = i;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.mDebugName = str;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        createObjectVariable.setName(str + ".left");
        createObjectVariable2.setName(str + ".top");
        createObjectVariable3.setName(str + ".right");
        createObjectVariable4.setName(str + ".bottom");
        if (this.mBaselineDistance > 0) {
            linearSystem.createObjectVariable(this.mBaseline).setName(str + ".baseline");
        }
    }

    public void createObjectVariables(LinearSystem linearSystem) {
        linearSystem.createObjectVariable(this.mLeft);
        linearSystem.createObjectVariable(this.mTop);
        linearSystem.createObjectVariable(this.mRight);
        linearSystem.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            linearSystem.createObjectVariable(this.mBaseline);
        }
    }

    public String toString() {
        String str = "";
        StringBuilder append = new StringBuilder().append(this.mType != null ? "type: " + this.mType + " " : str);
        if (this.mDebugName != null) {
            str = "id: " + this.mDebugName + " ";
        }
        return append.append(str).append("(").append(this.f35mX).append(", ").append(this.f36mY).append(") - (").append(this.mWidth).append(" x ").append(this.mHeight).append(")").toString();
    }

    public int getX() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget == null || !(constraintWidget instanceof ConstraintWidgetContainer)) {
            return this.f35mX;
        }
        return ((ConstraintWidgetContainer) constraintWidget).mPaddingLeft + this.f35mX;
    }

    public int getY() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget == null || !(constraintWidget instanceof ConstraintWidgetContainer)) {
            return this.f36mY;
        }
        return ((ConstraintWidgetContainer) constraintWidget).mPaddingTop + this.f36mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getOptimizerWrapWidth() {
        int i;
        int i2 = this.mWidth;
        if (this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i2;
        }
        if (this.mMatchConstraintDefaultWidth == 1) {
            i = Math.max(this.mMatchConstraintMinWidth, i2);
        } else {
            i = this.mMatchConstraintMinWidth;
            if (i > 0) {
                this.mWidth = i;
            } else {
                i = 0;
            }
        }
        int i3 = this.mMatchConstraintMaxWidth;
        return (i3 <= 0 || i3 >= i) ? i : i3;
    }

    public int getOptimizerWrapHeight() {
        int i;
        int i2 = this.mHeight;
        if (this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i2;
        }
        if (this.mMatchConstraintDefaultHeight == 1) {
            i = Math.max(this.mMatchConstraintMinHeight, i2);
        } else {
            i = this.mMatchConstraintMinHeight;
            if (i > 0) {
                this.mHeight = i;
            } else {
                i = 0;
            }
        }
        int i3 = this.mMatchConstraintMaxHeight;
        return (i3 <= 0 || i3 >= i) ? i : i3;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getLength(int i) {
        if (i == 0) {
            return getWidth();
        }
        if (i == 1) {
            return getHeight();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getRootX() {
        return this.f35mX + this.mOffsetX;
    }

    /* access modifiers changed from: protected */
    public int getRootY() {
        return this.f36mY + this.mOffsetY;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getLeft() {
        return getX();
    }

    public int getTop() {
        return getY();
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public int getHorizontalMargin() {
        ConstraintAnchor constraintAnchor = this.mLeft;
        int i = 0;
        if (constraintAnchor != null) {
            i = 0 + constraintAnchor.mMargin;
        }
        ConstraintAnchor constraintAnchor2 = this.mRight;
        return constraintAnchor2 != null ? i + constraintAnchor2.mMargin : i;
    }

    public int getVerticalMargin() {
        int i = 0;
        if (this.mLeft != null) {
            i = 0 + this.mTop.mMargin;
        }
        return this.mRight != null ? i + this.mBottom.mMargin : i;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public float getBiasPercent(int i) {
        if (i == 0) {
            return this.mHorizontalBiasPercent;
        }
        if (i == 1) {
            return this.mVerticalBiasPercent;
        }
        return -1.0f;
    }

    public boolean hasBaseline() {
        return this.hasBaseline;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int i) {
        this.f35mX = i;
    }

    public void setY(int i) {
        this.f36mY = i;
    }

    public void setOrigin(int i, int i2) {
        this.f35mX = i;
        this.f36mY = i2;
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    public void setGoneMargin(ConstraintAnchor.Type type, int i) {
        int i2 = C01551.f37x4c44d048[type.ordinal()];
        if (i2 == 1) {
            this.mLeft.mGoneMargin = i;
        } else if (i2 == 2) {
            this.mTop.mGoneMargin = i;
        } else if (i2 == 3) {
            this.mRight.mGoneMargin = i;
        } else if (i2 == 4) {
            this.mBottom.mGoneMargin = i;
        }
    }

    public void setWidth(int i) {
        this.mWidth = i;
        int i2 = this.mMinWidth;
        if (i < i2) {
            this.mWidth = i2;
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
        int i2 = this.mMinHeight;
        if (i < i2) {
            this.mHeight = i2;
        }
    }

    public void setLength(int i, int i2) {
        if (i2 == 0) {
            setWidth(i);
        } else if (i2 == 1) {
            setHeight(i);
        }
    }

    public void setHorizontalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultWidth = i;
        this.mMatchConstraintMinWidth = i2;
        if (i3 == Integer.MAX_VALUE) {
            i3 = 0;
        }
        this.mMatchConstraintMaxWidth = i3;
        this.mMatchConstraintPercentWidth = f;
        if (f > 0.0f && f < 1.0f && i == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setVerticalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultHeight = i;
        this.mMatchConstraintMinHeight = i2;
        if (i3 == Integer.MAX_VALUE) {
            i3 = 0;
        }
        this.mMatchConstraintMaxHeight = i3;
        this.mMatchConstraintPercentHeight = f;
        if (f > 0.0f && f < 1.0f && i == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDimensionRatio(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L_0x008e
            int r1 = r9.length()
            if (r1 != 0) goto L_0x000b
            goto L_0x008e
        L_0x000b:
            r1 = -1
            int r2 = r9.length()
            r3 = 44
            int r3 = r9.indexOf(r3)
            r4 = 0
            r5 = 1
            if (r3 <= 0) goto L_0x0037
            int r6 = r2 + -1
            if (r3 >= r6) goto L_0x0037
            java.lang.String r6 = r9.substring(r4, r3)
            java.lang.String r7 = "W"
            boolean r7 = r6.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x002c
            r1 = r4
            goto L_0x0035
        L_0x002c:
            java.lang.String r4 = "H"
            boolean r4 = r6.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x0035
            r1 = r5
        L_0x0035:
            int r4 = r3 + 1
        L_0x0037:
            r3 = 58
            int r3 = r9.indexOf(r3)
            if (r3 < 0) goto L_0x0075
            int r2 = r2 - r5
            if (r3 >= r2) goto L_0x0075
            java.lang.String r2 = r9.substring(r4, r3)
            int r3 = r3 + r5
            java.lang.String r9 = r9.substring(r3)
            int r3 = r2.length()
            if (r3 <= 0) goto L_0x0084
            int r3 = r9.length()
            if (r3 <= 0) goto L_0x0084
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ NumberFormatException -> 0x0084 }
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            int r3 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x0084
            int r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x0084
            if (r1 != r5) goto L_0x006f
            float r9 = r9 / r2
            float r9 = java.lang.Math.abs(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x006f:
            float r2 = r2 / r9
            float r9 = java.lang.Math.abs(r2)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x0075:
            java.lang.String r9 = r9.substring(r4)
            int r2 = r9.length()
            if (r2 <= 0) goto L_0x0084
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x0084:
            r9 = r0
        L_0x0085:
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x008d
            r8.mDimensionRatio = r9
            r8.mDimensionRatioSide = r1
        L_0x008d:
            return
        L_0x008e:
            r8.mDimensionRatio = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.setDimensionRatio(java.lang.String):void");
    }

    public void setDimensionRatio(float f, int i) {
        this.mDimensionRatio = f;
        this.mDimensionRatioSide = i;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setMinWidth(int i) {
        if (i < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i;
        }
    }

    public void setMinHeight(int i) {
        if (i < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i;
        }
    }

    public void setDimension(int i, int i2) {
        this.mWidth = i;
        int i3 = this.mMinWidth;
        if (i < i3) {
            this.mWidth = i3;
        }
        this.mHeight = i2;
        int i4 = this.mMinHeight;
        if (i2 < i4) {
            this.mHeight = i4;
        }
    }

    public void setFrame(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i3 - i;
        int i8 = i4 - i2;
        this.f35mX = i;
        this.f36mY = i2;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i7 < (i6 = this.mWidth)) {
            i7 = i6;
        }
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i8 < (i5 = this.mHeight)) {
            i8 = i5;
        }
        this.mWidth = i7;
        this.mHeight = i8;
        int i9 = this.mMinHeight;
        if (i8 < i9) {
            this.mHeight = i9;
        }
        int i10 = this.mMinWidth;
        if (i7 < i10) {
            this.mWidth = i10;
        }
    }

    public void setFrame(int i, int i2, int i3) {
        if (i3 == 0) {
            setHorizontalDimension(i, i2);
        } else if (i3 == 1) {
            setVerticalDimension(i, i2);
        }
    }

    public void setHorizontalDimension(int i, int i2) {
        this.f35mX = i;
        int i3 = i2 - i;
        this.mWidth = i3;
        int i4 = this.mMinWidth;
        if (i3 < i4) {
            this.mWidth = i4;
        }
    }

    public void setVerticalDimension(int i, int i2) {
        this.f36mY = i;
        int i3 = i2 - i;
        this.mHeight = i3;
        int i4 = this.mMinHeight;
        if (i3 < i4) {
            this.mHeight = i4;
        }
    }

    /* access modifiers changed from: package-private */
    public int getRelativePositioning(int i) {
        if (i == 0) {
            return this.mRelX;
        }
        if (i == 1) {
            return this.mRelY;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void setRelativePositioning(int i, int i2) {
        if (i2 == 0) {
            this.mRelX = i;
        } else if (i2 == 1) {
            this.mRelY = i;
        }
    }

    public void setBaselineDistance(int i) {
        this.mBaselineDistance = i;
        this.hasBaseline = i > 0;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setContainerItemSkip(int i) {
        if (i >= 0) {
            this.mContainerItemSkip = i;
        } else {
            this.mContainerItemSkip = 0;
        }
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public void setHorizontalWeight(float f) {
        this.mWeight[0] = f;
    }

    public void setVerticalWeight(float f) {
        this.mWeight[1] = f;
    }

    public void setHorizontalChainStyle(int i) {
        this.mHorizontalChainStyle = i;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int i) {
        this.mVerticalChainStyle = i;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void immediateConnect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i, int i2) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i, i2, true);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        if (constraintAnchor.getOwner() == this) {
            connect(constraintAnchor.getType(), constraintAnchor2.getOwner(), constraintAnchor2.getType(), i);
        }
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2) {
        connect(type, constraintWidget, type2, 0);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i) {
        boolean z;
        if (type == ConstraintAnchor.Type.CENTER) {
            if (type2 == ConstraintAnchor.Type.CENTER) {
                ConstraintAnchor anchor = getAnchor(ConstraintAnchor.Type.LEFT);
                ConstraintAnchor anchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
                ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.TOP);
                ConstraintAnchor anchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                boolean z2 = true;
                if ((anchor == null || !anchor.isConnected()) && (anchor2 == null || !anchor2.isConnected())) {
                    connect(ConstraintAnchor.Type.LEFT, constraintWidget, ConstraintAnchor.Type.LEFT, 0);
                    connect(ConstraintAnchor.Type.RIGHT, constraintWidget, ConstraintAnchor.Type.RIGHT, 0);
                    z = true;
                } else {
                    z = false;
                }
                if ((anchor3 == null || !anchor3.isConnected()) && (anchor4 == null || !anchor4.isConnected())) {
                    connect(ConstraintAnchor.Type.TOP, constraintWidget, ConstraintAnchor.Type.TOP, 0);
                    connect(ConstraintAnchor.Type.BOTTOM, constraintWidget, ConstraintAnchor.Type.BOTTOM, 0);
                } else {
                    z2 = false;
                }
                if (z && z2) {
                    getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.CENTER), 0);
                } else if (z) {
                    getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.CENTER_X), 0);
                } else if (z2) {
                    getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.CENTER_Y), 0);
                }
            } else if (type2 == ConstraintAnchor.Type.LEFT || type2 == ConstraintAnchor.Type.RIGHT) {
                connect(ConstraintAnchor.Type.LEFT, constraintWidget, type2, 0);
                connect(ConstraintAnchor.Type.RIGHT, constraintWidget, type2, 0);
                getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget.getAnchor(type2), 0);
            } else if (type2 == ConstraintAnchor.Type.TOP || type2 == ConstraintAnchor.Type.BOTTOM) {
                connect(ConstraintAnchor.Type.TOP, constraintWidget, type2, 0);
                connect(ConstraintAnchor.Type.BOTTOM, constraintWidget, type2, 0);
                getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget.getAnchor(type2), 0);
            }
        } else if (type == ConstraintAnchor.Type.CENTER_X && (type2 == ConstraintAnchor.Type.LEFT || type2 == ConstraintAnchor.Type.RIGHT)) {
            ConstraintAnchor anchor5 = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor anchor6 = constraintWidget.getAnchor(type2);
            ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.RIGHT);
            anchor5.connect(anchor6, 0);
            anchor7.connect(anchor6, 0);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(anchor6, 0);
        } else if (type == ConstraintAnchor.Type.CENTER_Y && (type2 == ConstraintAnchor.Type.TOP || type2 == ConstraintAnchor.Type.BOTTOM)) {
            ConstraintAnchor anchor8 = constraintWidget.getAnchor(type2);
            getAnchor(ConstraintAnchor.Type.TOP).connect(anchor8, 0);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(anchor8, 0);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(anchor8, 0);
        } else if (type == ConstraintAnchor.Type.CENTER_X && type2 == ConstraintAnchor.Type.CENTER_X) {
            getAnchor(ConstraintAnchor.Type.LEFT).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT), 0);
            getAnchor(ConstraintAnchor.Type.RIGHT).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT), 0);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintWidget.getAnchor(type2), 0);
        } else if (type == ConstraintAnchor.Type.CENTER_Y && type2 == ConstraintAnchor.Type.CENTER_Y) {
            getAnchor(ConstraintAnchor.Type.TOP).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.TOP), 0);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM), 0);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(constraintWidget.getAnchor(type2), 0);
        } else {
            ConstraintAnchor anchor9 = getAnchor(type);
            ConstraintAnchor anchor10 = constraintWidget.getAnchor(type2);
            if (anchor9.isValidConnection(anchor10)) {
                if (type == ConstraintAnchor.Type.BASELINE) {
                    ConstraintAnchor anchor11 = getAnchor(ConstraintAnchor.Type.TOP);
                    ConstraintAnchor anchor12 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                    if (anchor11 != null) {
                        anchor11.reset();
                    }
                    if (anchor12 != null) {
                        anchor12.reset();
                    }
                    i = 0;
                } else if (type == ConstraintAnchor.Type.TOP || type == ConstraintAnchor.Type.BOTTOM) {
                    ConstraintAnchor anchor13 = getAnchor(ConstraintAnchor.Type.BASELINE);
                    if (anchor13 != null) {
                        anchor13.reset();
                    }
                    ConstraintAnchor anchor14 = getAnchor(ConstraintAnchor.Type.CENTER);
                    if (anchor14.getTarget() != anchor10) {
                        anchor14.reset();
                    }
                    ConstraintAnchor opposite = getAnchor(type).getOpposite();
                    ConstraintAnchor anchor15 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
                    if (anchor15.isConnected()) {
                        opposite.reset();
                        anchor15.reset();
                    }
                } else if (type == ConstraintAnchor.Type.LEFT || type == ConstraintAnchor.Type.RIGHT) {
                    ConstraintAnchor anchor16 = getAnchor(ConstraintAnchor.Type.CENTER);
                    if (anchor16.getTarget() != anchor10) {
                        anchor16.reset();
                    }
                    ConstraintAnchor opposite2 = getAnchor(type).getOpposite();
                    ConstraintAnchor anchor17 = getAnchor(ConstraintAnchor.Type.CENTER_X);
                    if (anchor17.isConnected()) {
                        opposite2.reset();
                        anchor17.reset();
                    }
                }
                anchor9.connect(anchor10, i);
            }
        }
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor) {
        if (getParent() == null || !(getParent() instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            ConstraintAnchor anchor = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor anchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
            ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor anchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
            ConstraintAnchor anchor5 = getAnchor(ConstraintAnchor.Type.CENTER);
            ConstraintAnchor anchor6 = getAnchor(ConstraintAnchor.Type.CENTER_X);
            ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
            if (constraintAnchor == anchor5) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor.reset();
                    anchor2.reset();
                }
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor6) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget().getOwner() == anchor2.getTarget().getOwner()) {
                    anchor.reset();
                    anchor2.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor7) {
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget().getOwner() == anchor4.getTarget().getOwner()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor || constraintAnchor == anchor2) {
                if (anchor.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor5.reset();
                }
            } else if ((constraintAnchor == anchor3 || constraintAnchor == anchor4) && anchor3.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                anchor5.reset();
            }
            constraintAnchor.reset();
        }
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i = 0; i < size; i++) {
                this.mAnchors.get(i).reset();
            }
        }
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (C01551.f37x4c44d048[type.ordinal()]) {
            case 1:
                return this.mLeft;
            case 2:
                return this.mTop;
            case 3:
                return this.mRight;
            case 4:
                return this.mBottom;
            case 5:
                return this.mBaseline;
            case 6:
                return this.mCenter;
            case 7:
                return this.mCenterX;
            case 8:
                return this.mCenterY;
            case 9:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public DimensionBehaviour getDimensionBehaviour(int i) {
        if (i == 0) {
            return getHorizontalDimensionBehaviour();
        }
        if (i == 1) {
            return getVerticalDimensionBehaviour();
        }
        return null;
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[0] = dimensionBehaviour;
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[1] = dimensionBehaviour;
    }

    public boolean isInHorizontalChain() {
        if (this.mLeft.mTarget == null || this.mLeft.mTarget.mTarget != this.mLeft) {
            return this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight;
        }
        return true;
    }

    public ConstraintWidget getPreviousChainMember(int i) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i == 0) {
            if (this.mLeft.mTarget == null || this.mLeft.mTarget.mTarget != (constraintAnchor2 = this.mLeft)) {
                return null;
            }
            return constraintAnchor2.mTarget.mOwner;
        } else if (i == 1 && this.mTop.mTarget != null && this.mTop.mTarget.mTarget == (constraintAnchor = this.mTop)) {
            return constraintAnchor.mTarget.mOwner;
        } else {
            return null;
        }
    }

    public ConstraintWidget getNextChainMember(int i) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i == 0) {
            if (this.mRight.mTarget == null || this.mRight.mTarget.mTarget != (constraintAnchor2 = this.mRight)) {
                return null;
            }
            return constraintAnchor2.mTarget.mOwner;
        } else if (i == 1 && this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == (constraintAnchor = this.mBottom)) {
            return constraintAnchor.mTarget.mOwner;
        } else {
            return null;
        }
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        ConstraintAnchor constraintAnchor;
        ConstraintWidget constraintWidget;
        ConstraintAnchor constraintAnchor2;
        if (!isInHorizontalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget2 = this;
        ConstraintWidget constraintWidget3 = null;
        while (constraintWidget3 == null && constraintWidget2 != null) {
            ConstraintAnchor anchor = constraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT);
            if (anchor == null) {
                constraintAnchor = null;
            } else {
                constraintAnchor = anchor.getTarget();
            }
            if (constraintAnchor == null) {
                constraintWidget = null;
            } else {
                constraintWidget = constraintAnchor.getOwner();
            }
            if (constraintWidget == getParent()) {
                return constraintWidget2;
            }
            if (constraintWidget == null) {
                constraintAnchor2 = null;
            } else {
                constraintAnchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget();
            }
            if (constraintAnchor2 == null || constraintAnchor2.getOwner() == constraintWidget2) {
                constraintWidget2 = constraintWidget;
            } else {
                constraintWidget3 = constraintWidget2;
            }
        }
        return constraintWidget3;
    }

    public boolean isInVerticalChain() {
        if (this.mTop.mTarget == null || this.mTop.mTarget.mTarget != this.mTop) {
            return this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom;
        }
        return true;
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        ConstraintAnchor constraintAnchor;
        ConstraintWidget constraintWidget;
        ConstraintAnchor constraintAnchor2;
        if (!isInVerticalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget2 = this;
        ConstraintWidget constraintWidget3 = null;
        while (constraintWidget3 == null && constraintWidget2 != null) {
            ConstraintAnchor anchor = constraintWidget2.getAnchor(ConstraintAnchor.Type.TOP);
            if (anchor == null) {
                constraintAnchor = null;
            } else {
                constraintAnchor = anchor.getTarget();
            }
            if (constraintAnchor == null) {
                constraintWidget = null;
            } else {
                constraintWidget = constraintAnchor.getOwner();
            }
            if (constraintWidget == getParent()) {
                return constraintWidget2;
            }
            if (constraintWidget == null) {
                constraintAnchor2 = null;
            } else {
                constraintAnchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget();
            }
            if (constraintAnchor2 == null || constraintAnchor2.getOwner() == constraintWidget2) {
                constraintWidget2 = constraintWidget;
            } else {
                constraintWidget3 = constraintWidget2;
            }
        }
        return constraintWidget3;
    }

    private boolean isChainHead(int i) {
        int i2 = i * 2;
        if (this.mListAnchors[i2].mTarget != null) {
            ConstraintAnchor constraintAnchor = this.mListAnchors[i2].mTarget.mTarget;
            ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
            if (constraintAnchor != constraintAnchorArr[i2]) {
                int i3 = i2 + 1;
                return constraintAnchorArr[i3].mTarget != null && this.mListAnchors[i3].mTarget.mTarget == this.mListAnchors[i3];
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v3, resolved type: int} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x027a  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0284  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0294  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x0297  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x029b  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x029e  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02b7  */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x038c  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x03ae  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x03f7  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x0409  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x040b  */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x040e  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x04cd  */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x04d3  */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x04fe  */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x0508  */
    /* JADX WARNING: Removed duplicated region for block: B:254:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addToSolver(androidx.constraintlayout.solver.LinearSystem r44) {
        /*
            r43 = this;
            r13 = r43
            r9 = r44
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r13.mLeft
            androidx.constraintlayout.solver.SolverVariable r7 = r9.createObjectVariable(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r13.mRight
            androidx.constraintlayout.solver.SolverVariable r6 = r9.createObjectVariable(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r13.mTop
            androidx.constraintlayout.solver.SolverVariable r4 = r9.createObjectVariable(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r13.mBottom
            androidx.constraintlayout.solver.SolverVariable r3 = r9.createObjectVariable(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r13.mBaseline
            androidx.constraintlayout.solver.SolverVariable r1 = r9.createObjectVariable(r0)
            androidx.constraintlayout.solver.Metrics r0 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            r10 = 1
            if (r0 == 0) goto L_0x002f
            androidx.constraintlayout.solver.Metrics r0 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            long r14 = r0.widgets
            long r14 = r14 + r10
            r0.widgets = r14
        L_0x002f:
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r0 = r13.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.start
            boolean r0 = r0.resolved
            r12 = 0
            if (r0 == 0) goto L_0x00e1
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r0 = r13.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.end
            boolean r0 = r0.resolved
            if (r0 == 0) goto L_0x00e1
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r13.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.start
            boolean r0 = r0.resolved
            if (r0 == 0) goto L_0x00e1
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r13.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.end
            boolean r0 = r0.resolved
            if (r0 == 0) goto L_0x00e1
            androidx.constraintlayout.solver.Metrics r0 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            if (r0 == 0) goto L_0x005b
            androidx.constraintlayout.solver.Metrics r0 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            long r14 = r0.graphSolved
            long r14 = r14 + r10
            r0.graphSolved = r14
        L_0x005b:
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r0 = r13.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.start
            int r0 = r0.value
            r9.addEquality(r7, r0)
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r0 = r13.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.end
            int r0 = r0.value
            r9.addEquality(r6, r0)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r13.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.start
            int r0 = r0.value
            r9.addEquality(r4, r0)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r13.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.end
            int r0 = r0.value
            r9.addEquality(r3, r0)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r13.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.baseline
            int r0 = r0.value
            r9.addEquality(r1, r0)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r13.mParent
            if (r0 == 0) goto L_0x00e0
            if (r0 == 0) goto L_0x0098
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r0 = r0[r12]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r1) goto L_0x0098
            r0 = 1
            goto L_0x0099
        L_0x0098:
            r0 = r12
        L_0x0099:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r13.mParent
            if (r1 == 0) goto L_0x00a8
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r1.mListDimensionBehaviors
            r2 = 1
            r1 = r1[r2]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r1 != r2) goto L_0x00a8
            r1 = 1
            goto L_0x00a9
        L_0x00a8:
            r1 = r12
        L_0x00a9:
            if (r0 == 0) goto L_0x00c4
            boolean[] r0 = r13.isTerminalWidget
            boolean r0 = r0[r12]
            if (r0 == 0) goto L_0x00c4
            boolean r0 = r43.isInHorizontalChain()
            if (r0 != 0) goto L_0x00c4
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r13.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mRight
            androidx.constraintlayout.solver.SolverVariable r0 = r9.createObjectVariable(r0)
            r2 = 8
            r9.addGreaterThan(r0, r6, r12, r2)
        L_0x00c4:
            if (r1 == 0) goto L_0x00e0
            boolean[] r0 = r13.isTerminalWidget
            r1 = 1
            boolean r0 = r0[r1]
            if (r0 == 0) goto L_0x00e0
            boolean r0 = r43.isInVerticalChain()
            if (r0 != 0) goto L_0x00e0
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r13.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mBottom
            androidx.constraintlayout.solver.SolverVariable r0 = r9.createObjectVariable(r0)
            r1 = 8
            r9.addGreaterThan(r0, r3, r12, r1)
        L_0x00e0:
            return
        L_0x00e1:
            androidx.constraintlayout.solver.Metrics r0 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            if (r0 == 0) goto L_0x00ec
            androidx.constraintlayout.solver.Metrics r0 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            long r14 = r0.linearSolved
            long r14 = r14 + r10
            r0.linearSolved = r14
        L_0x00ec:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r13.mParent
            if (r0 == 0) goto L_0x0183
            if (r0 == 0) goto L_0x00fc
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r0 = r0[r12]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r2) goto L_0x00fc
            r0 = 1
            goto L_0x00fd
        L_0x00fc:
            r0 = r12
        L_0x00fd:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r13.mParent
            if (r2 == 0) goto L_0x010c
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r2.mListDimensionBehaviors
            r5 = 1
            r2 = r2[r5]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 != r5) goto L_0x010c
            r2 = 1
            goto L_0x010d
        L_0x010c:
            r2 = r12
        L_0x010d:
            boolean r5 = r13.isChainHead(r12)
            if (r5 == 0) goto L_0x011c
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r13.mParent
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r5 = (androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer) r5
            r5.addChain(r13, r12)
            r5 = 1
            goto L_0x0120
        L_0x011c:
            boolean r5 = r43.isInHorizontalChain()
        L_0x0120:
            r8 = 1
            boolean r10 = r13.isChainHead(r8)
            if (r10 == 0) goto L_0x0130
            androidx.constraintlayout.solver.widgets.ConstraintWidget r10 = r13.mParent
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = (androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer) r10
            r10.addChain(r13, r8)
            r8 = 1
            goto L_0x0134
        L_0x0130:
            boolean r8 = r43.isInVerticalChain()
        L_0x0134:
            if (r5 != 0) goto L_0x0156
            if (r0 == 0) goto L_0x0156
            int r10 = r13.mVisibility
            r11 = 8
            if (r10 == r11) goto L_0x0156
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r13.mLeft
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 != 0) goto L_0x0156
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r13.mRight
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 != 0) goto L_0x0156
            androidx.constraintlayout.solver.widgets.ConstraintWidget r10 = r13.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r10.mRight
            androidx.constraintlayout.solver.SolverVariable r10 = r9.createObjectVariable(r10)
            r11 = 1
            r9.addGreaterThan(r10, r6, r12, r11)
        L_0x0156:
            if (r8 != 0) goto L_0x017c
            if (r2 == 0) goto L_0x017c
            int r10 = r13.mVisibility
            r11 = 8
            if (r10 == r11) goto L_0x017c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r13.mTop
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 != 0) goto L_0x017c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r13.mBottom
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 != 0) goto L_0x017c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r13.mBaseline
            if (r10 != 0) goto L_0x017c
            androidx.constraintlayout.solver.widgets.ConstraintWidget r10 = r13.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r10.mBottom
            androidx.constraintlayout.solver.SolverVariable r10 = r9.createObjectVariable(r10)
            r11 = 1
            r9.addGreaterThan(r10, r3, r12, r11)
        L_0x017c:
            r15 = r0
            r0 = r2
            r28 = r5
            r27 = r8
            goto L_0x0189
        L_0x0183:
            r0 = r12
            r15 = r0
            r27 = r15
            r28 = r27
        L_0x0189:
            int r2 = r13.mWidth
            int r5 = r13.mMinWidth
            if (r2 >= r5) goto L_0x0190
            r2 = r5
        L_0x0190:
            int r5 = r13.mHeight
            int r8 = r13.mMinHeight
            if (r5 >= r8) goto L_0x0197
            r5 = r8
        L_0x0197:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r8 = r13.mListDimensionBehaviors
            r8 = r8[r12]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r8 == r10) goto L_0x01a1
            r8 = 1
            goto L_0x01a2
        L_0x01a1:
            r8 = r12
        L_0x01a2:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r10 = r13.mListDimensionBehaviors
            r11 = 1
            r10 = r10[r11]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r10 == r11) goto L_0x01ad
            r10 = 1
            goto L_0x01ae
        L_0x01ad:
            r10 = r12
        L_0x01ae:
            int r11 = r13.mDimensionRatioSide
            r13.mResolvedDimensionRatioSide = r11
            float r11 = r13.mDimensionRatio
            r13.mResolvedDimensionRatio = r11
            int r14 = r13.mMatchConstraintDefaultWidth
            int r12 = r13.mMatchConstraintDefaultHeight
            r19 = 0
            int r11 = (r11 > r19 ? 1 : (r11 == r19 ? 0 : -1))
            r19 = 4
            r20 = r2
            if (r11 <= 0) goto L_0x0263
            int r11 = r13.mVisibility
            r2 = 8
            if (r11 == r2) goto L_0x0263
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r13.mListDimensionBehaviors
            r11 = 0
            r2 = r2[r11]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r22 = r1
            r1 = 3
            if (r2 != r11) goto L_0x01d9
            if (r14 != 0) goto L_0x01d9
            r14 = r1
        L_0x01d9:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r13.mListDimensionBehaviors
            r11 = 1
            r2 = r2[r11]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r11) goto L_0x01e5
            if (r12 != 0) goto L_0x01e5
            r12 = r1
        L_0x01e5:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r13.mListDimensionBehaviors
            r11 = 0
            r2 = r2[r11]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r11) goto L_0x01ff
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r13.mListDimensionBehaviors
            r11 = 1
            r2 = r2[r11]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r11) goto L_0x01ff
            if (r14 != r1) goto L_0x01ff
            if (r12 != r1) goto L_0x01ff
            r13.setupDimensionRatio(r15, r0, r8, r10)
            goto L_0x0259
        L_0x01ff:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r13.mListDimensionBehaviors
            r8 = 0
            r2 = r2[r8]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r10) goto L_0x0229
            if (r14 != r1) goto L_0x0229
            r13.mResolvedDimensionRatioSide = r8
            float r1 = r13.mResolvedDimensionRatio
            int r2 = r13.mHeight
            float r2 = (float) r2
            float r1 = r1 * r2
            int r2 = (int) r1
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r13.mListDimensionBehaviors
            r8 = 1
            r1 = r1[r8]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r29 = r5
            if (r1 == r10) goto L_0x0223
            r30 = r12
            r31 = r19
            goto L_0x026d
        L_0x0223:
            r1 = r8
            r30 = r12
            r31 = r14
            goto L_0x026e
        L_0x0229:
            r8 = 1
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r13.mListDimensionBehaviors
            r2 = r2[r8]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r10) goto L_0x0259
            if (r12 != r1) goto L_0x0259
            r13.mResolvedDimensionRatioSide = r8
            int r1 = r13.mDimensionRatioSide
            r2 = -1
            if (r1 != r2) goto L_0x0242
            r1 = 1065353216(0x3f800000, float:1.0)
            float r2 = r13.mResolvedDimensionRatio
            float r1 = r1 / r2
            r13.mResolvedDimensionRatio = r1
        L_0x0242:
            float r1 = r13.mResolvedDimensionRatio
            int r2 = r13.mWidth
            float r2 = (float) r2
            float r1 = r1 * r2
            int r5 = (int) r1
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r13.mListDimensionBehaviors
            r2 = 0
            r1 = r1[r2]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 == r2) goto L_0x0259
            r29 = r5
            r31 = r14
            r30 = r19
            goto L_0x026b
        L_0x0259:
            r29 = r5
            r30 = r12
            r31 = r14
            r2 = r20
            r1 = 1
            goto L_0x026e
        L_0x0263:
            r22 = r1
            r29 = r5
            r30 = r12
            r31 = r14
        L_0x026b:
            r2 = r20
        L_0x026d:
            r1 = 0
        L_0x026e:
            int[] r5 = r13.mResolvedMatchConstraintDefault
            r8 = 0
            r5[r8] = r31
            r8 = 1
            r5[r8] = r30
            r13.mResolvedHasRatio = r1
            if (r1 == 0) goto L_0x0284
            int r5 = r13.mResolvedDimensionRatioSide
            r8 = -1
            if (r5 == 0) goto L_0x0281
            if (r5 != r8) goto L_0x0285
        L_0x0281:
            r19 = 1
            goto L_0x0287
        L_0x0284:
            r8 = -1
        L_0x0285:
            r19 = 0
        L_0x0287:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r13.mListDimensionBehaviors
            r10 = 0
            r5 = r5[r10]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r5 != r10) goto L_0x0297
            boolean r5 = r13 instanceof androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer
            if (r5 == 0) goto L_0x0297
            r20 = 1
            goto L_0x0299
        L_0x0297:
            r20 = 0
        L_0x0299:
            if (r20 == 0) goto L_0x029e
            r21 = 0
            goto L_0x02a0
        L_0x029e:
            r21 = r2
        L_0x02a0:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r13.mCenter
            boolean r2 = r2.isConnected()
            r14 = 1
            r32 = r2 ^ 1
            boolean[] r2 = r13.mIsInBarrier
            r5 = 0
            boolean r26 = r2[r5]
            boolean r33 = r2[r14]
            int r2 = r13.mHorizontalResolution
            r12 = 2
            r34 = 0
            if (r2 == r12) goto L_0x038c
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r13.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r2.start
            boolean r2 = r2.resolved
            if (r2 == 0) goto L_0x030c
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r13.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r2.end
            boolean r2 = r2.resolved
            if (r2 != 0) goto L_0x02c8
            goto L_0x030c
        L_0x02c8:
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r13.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r2.start
            int r2 = r2.value
            r9.addEquality(r7, r2)
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r13.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r2.end
            int r2 = r2.value
            r9.addEquality(r6, r2)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r13.mParent
            if (r2 == 0) goto L_0x02fa
            if (r15 == 0) goto L_0x02fa
            boolean[] r2 = r13.isTerminalWidget
            r5 = 0
            boolean r2 = r2[r5]
            if (r2 == 0) goto L_0x02fa
            boolean r2 = r43.isInHorizontalChain()
            if (r2 != 0) goto L_0x02fa
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r13.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mRight
            androidx.constraintlayout.solver.SolverVariable r2 = r9.createObjectVariable(r2)
            r11 = 8
            r9.addGreaterThan(r2, r6, r5, r11)
        L_0x02fa:
            r36 = r0
            r39 = r1
            r40 = r3
            r41 = r4
            r42 = r6
            r35 = r7
            r37 = r15
            r38 = r22
            goto L_0x039e
        L_0x030c:
            r11 = 8
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r13.mParent
            if (r2 == 0) goto L_0x031b
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mRight
            androidx.constraintlayout.solver.SolverVariable r2 = r9.createObjectVariable(r2)
            r17 = r2
            goto L_0x031d
        L_0x031b:
            r17 = r34
        L_0x031d:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r13.mParent
            if (r2 == 0) goto L_0x032a
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mLeft
            androidx.constraintlayout.solver.SolverVariable r2 = r9.createObjectVariable(r2)
            r35 = r2
            goto L_0x032c
        L_0x032a:
            r35 = r34
        L_0x032c:
            r2 = 1
            r10 = r8
            boolean[] r5 = r13.isTerminalWidget
            r16 = 0
            boolean r5 = r5[r16]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r8 = r13.mListDimensionBehaviors
            r8 = r8[r16]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r13.mLeft
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r13.mRight
            r18 = 8
            int r12 = r13.f35mX
            r2 = r16
            int r14 = r13.mMinWidth
            r36 = r0
            int[] r0 = r13.mMaxDimension
            r0 = r0[r2]
            r37 = r15
            r15 = r0
            float r0 = r13.mHorizontalBiasPercent
            r16 = r0
            int r0 = r13.mMatchConstraintMinWidth
            r23 = r0
            int r0 = r13.mMatchConstraintMaxWidth
            r24 = r0
            float r0 = r13.mMatchConstraintPercentWidth
            r25 = r0
            r0 = r43
            r39 = r1
            r38 = r22
            r1 = r44
            r40 = r3
            r3 = r37
            r41 = r4
            r4 = r36
            r42 = r6
            r6 = r35
            r35 = r7
            r7 = r17
            r9 = r20
            r13 = r21
            r17 = r19
            r18 = r28
            r19 = r27
            r20 = r26
            r21 = r31
            r22 = r30
            r26 = r32
            r2 = 1
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            goto L_0x039c
        L_0x038c:
            r36 = r0
            r39 = r1
            r40 = r3
            r41 = r4
            r42 = r6
            r35 = r7
            r37 = r15
            r38 = r22
        L_0x039c:
            r13 = r43
        L_0x039e:
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r13.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.start
            boolean r0 = r0.resolved
            if (r0 == 0) goto L_0x03f7
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r13.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.end
            boolean r0 = r0.resolved
            if (r0 == 0) goto L_0x03f7
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r13.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.start
            int r0 = r0.value
            r9 = r44
            r7 = r41
            r9.addEquality(r7, r0)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r13.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.end
            int r0 = r0.value
            r6 = r40
            r9.addEquality(r6, r0)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r13.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.baseline
            int r0 = r0.value
            r1 = r38
            r9.addEquality(r1, r0)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r13.mParent
            if (r0 == 0) goto L_0x03f1
            if (r27 != 0) goto L_0x03f1
            if (r36 == 0) goto L_0x03f1
            boolean[] r2 = r13.isTerminalWidget
            r4 = 1
            boolean r2 = r2[r4]
            if (r2 == 0) goto L_0x03ed
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mBottom
            androidx.constraintlayout.solver.SolverVariable r0 = r9.createObjectVariable(r0)
            r2 = 8
            r3 = 0
            r9.addGreaterThan(r0, r6, r3, r2)
            goto L_0x03f5
        L_0x03ed:
            r2 = 8
            r3 = 0
            goto L_0x03f5
        L_0x03f1:
            r2 = 8
            r3 = 0
            r4 = 1
        L_0x03f5:
            r14 = r3
            goto L_0x0404
        L_0x03f7:
            r9 = r44
            r1 = r38
            r6 = r40
            r7 = r41
            r2 = 8
            r3 = 0
            r4 = 1
            r14 = r4
        L_0x0404:
            int r0 = r13.mVerticalResolution
            r5 = 2
            if (r0 != r5) goto L_0x040b
            r12 = r3
            goto L_0x040c
        L_0x040b:
            r12 = r14
        L_0x040c:
            if (r12 == 0) goto L_0x04cd
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r13.mListDimensionBehaviors
            r0 = r0[r4]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r5) goto L_0x041d
            boolean r0 = r13 instanceof androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer
            if (r0 == 0) goto L_0x041d
            r17 = r4
            goto L_0x041f
        L_0x041d:
            r17 = r3
        L_0x041f:
            if (r17 == 0) goto L_0x0423
            r29 = r3
        L_0x0423:
            if (r39 == 0) goto L_0x042f
            int r0 = r13.mResolvedDimensionRatioSide
            if (r0 == r4) goto L_0x042c
            r5 = -1
            if (r0 != r5) goto L_0x042f
        L_0x042c:
            r18 = r4
            goto L_0x0431
        L_0x042f:
            r18 = r3
        L_0x0431:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r13.mParent
            if (r0 == 0) goto L_0x043c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mBottom
            androidx.constraintlayout.solver.SolverVariable r0 = r9.createObjectVariable(r0)
            goto L_0x043e
        L_0x043c:
            r0 = r34
        L_0x043e:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r13.mParent
            if (r5 == 0) goto L_0x044a
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r5.mTop
            androidx.constraintlayout.solver.SolverVariable r5 = r9.createObjectVariable(r5)
            r34 = r5
        L_0x044a:
            int r5 = r13.mBaselineDistance
            if (r5 > 0) goto L_0x0452
            int r5 = r13.mVisibility
            if (r5 != r2) goto L_0x0480
        L_0x0452:
            int r5 = r43.getBaselineDistance()
            r9.addEquality(r1, r7, r5, r2)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r13.mBaseline
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x0479
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r13.mBaseline
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            androidx.constraintlayout.solver.SolverVariable r5 = r9.createObjectVariable(r5)
            r9.addEquality(r1, r5, r3, r2)
            if (r36 == 0) goto L_0x0476
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r13.mBottom
            androidx.constraintlayout.solver.SolverVariable r1 = r9.createObjectVariable(r1)
            r2 = 5
            r9.addGreaterThan(r0, r1, r3, r2)
        L_0x0476:
            r26 = r3
            goto L_0x0482
        L_0x0479:
            int r5 = r13.mVisibility
            if (r5 != r2) goto L_0x0480
            r9.addEquality(r1, r7, r3, r2)
        L_0x0480:
            r26 = r32
        L_0x0482:
            r2 = 0
            boolean[] r1 = r13.isTerminalWidget
            boolean r5 = r1[r4]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r13.mListDimensionBehaviors
            r8 = r1[r4]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r13.mTop
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r13.mBottom
            int r12 = r13.f36mY
            int r14 = r13.mMinHeight
            int[] r1 = r13.mMaxDimension
            r15 = r1[r4]
            float r1 = r13.mVerticalBiasPercent
            r16 = r1
            int r1 = r13.mMatchConstraintMinHeight
            r23 = r1
            int r1 = r13.mMatchConstraintMaxHeight
            r24 = r1
            float r1 = r13.mMatchConstraintPercentHeight
            r25 = r1
            r19 = r0
            r0 = r43
            r1 = r44
            r3 = r36
            r4 = r37
            r32 = r6
            r6 = r34
            r34 = r7
            r7 = r19
            r9 = r17
            r13 = r29
            r17 = r18
            r18 = r27
            r19 = r28
            r20 = r33
            r21 = r30
            r22 = r31
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            goto L_0x04d1
        L_0x04cd:
            r32 = r6
            r34 = r7
        L_0x04d1:
            if (r39 == 0) goto L_0x04fe
            r6 = 8
            r7 = r43
            int r0 = r7.mResolvedDimensionRatioSide
            r1 = 1
            if (r0 != r1) goto L_0x04ec
            float r5 = r7.mResolvedDimensionRatio
            r0 = r44
            r1 = r32
            r2 = r34
            r3 = r42
            r4 = r35
            r0.addRatio(r1, r2, r3, r4, r5, r6)
            goto L_0x0500
        L_0x04ec:
            float r5 = r7.mResolvedDimensionRatio
            r6 = 8
            r0 = r44
            r1 = r42
            r2 = r35
            r3 = r32
            r4 = r34
            r0.addRatio(r1, r2, r3, r4, r5, r6)
            goto L_0x0500
        L_0x04fe:
            r7 = r43
        L_0x0500:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r7.mCenter
            boolean r0 = r0.isConnected()
            if (r0 == 0) goto L_0x0528
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r7.mCenter
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.getTarget()
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r0.getOwner()
            float r1 = r7.mCircleConstraintAngle
            r2 = 1119092736(0x42b40000, float:90.0)
            float r1 = r1 + r2
            double r1 = (double) r1
            double r1 = java.lang.Math.toRadians(r1)
            float r1 = (float) r1
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r7.mCenter
            int r2 = r2.getMargin()
            r3 = r44
            r3.addCenterPoint(r7, r0, r1, r2)
        L_0x0528:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.addToSolver(androidx.constraintlayout.solver.LinearSystem):void");
    }

    /* access modifiers changed from: package-private */
    public boolean addFirst() {
        return (this instanceof VirtualLayout) || (this instanceof Guideline);
    }

    public void setupDimensionRatio(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z3 && !z4) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z3 && z4) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            int i = this.mMatchConstraintMinWidth;
            if (i > 0 && this.mMatchConstraintMinHeight == 0) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (i == 0 && this.mMatchConstraintMinHeight > 0) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:286:0x0457, code lost:
        if (r0.mListDimensionBehaviors[1] == androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L_0x045c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01d1  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x02f1  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x0321  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x032e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x032f  */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x03f7 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x0410  */
    /* JADX WARNING: Removed duplicated region for block: B:270:0x041e A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:290:0x045f  */
    /* JADX WARNING: Removed duplicated region for block: B:292:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:296:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:299:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00dc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyConstraints(androidx.constraintlayout.solver.LinearSystem r32, boolean r33, boolean r34, boolean r35, boolean r36, androidx.constraintlayout.solver.SolverVariable r37, androidx.constraintlayout.solver.SolverVariable r38, androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour r39, boolean r40, androidx.constraintlayout.solver.widgets.ConstraintAnchor r41, androidx.constraintlayout.solver.widgets.ConstraintAnchor r42, int r43, int r44, int r45, int r46, float r47, boolean r48, boolean r49, boolean r50, boolean r51, int r52, int r53, int r54, int r55, float r56, boolean r57) {
        /*
            r31 = this;
            r0 = r31
            r10 = r32
            r11 = r37
            r12 = r38
            r13 = r41
            r14 = r42
            r15 = r45
            r1 = r46
            r2 = r53
            r3 = r54
            r4 = r55
            androidx.constraintlayout.solver.SolverVariable r9 = r10.createObjectVariable(r13)
            androidx.constraintlayout.solver.SolverVariable r8 = r10.createObjectVariable(r14)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r41.getTarget()
            androidx.constraintlayout.solver.SolverVariable r7 = r10.createObjectVariable(r5)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r42.getTarget()
            androidx.constraintlayout.solver.SolverVariable r6 = r10.createObjectVariable(r5)
            androidx.constraintlayout.solver.Metrics r5 = androidx.constraintlayout.solver.LinearSystem.getMetrics()
            if (r5 == 0) goto L_0x0040
            androidx.constraintlayout.solver.Metrics r5 = androidx.constraintlayout.solver.LinearSystem.getMetrics()
            long r12 = r5.nonresolvedWidgets
            r16 = 1
            long r12 = r12 + r16
            r5.nonresolvedWidgets = r12
        L_0x0040:
            boolean r12 = r41.isConnected()
            boolean r13 = r42.isConnected()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r0.mCenter
            boolean r16 = r5.isConnected()
            if (r13 == 0) goto L_0x0053
            int r5 = r12 + 1
            goto L_0x0054
        L_0x0053:
            r5 = r12
        L_0x0054:
            if (r16 == 0) goto L_0x0058
            int r5 = r5 + 1
        L_0x0058:
            if (r48 == 0) goto L_0x005d
            r18 = 3
            goto L_0x005f
        L_0x005d:
            r18 = r52
        L_0x005f:
            int[] r17 = androidx.constraintlayout.solver.widgets.ConstraintWidget.C01551.f38xdde91696
            int r19 = r39.ordinal()
            r2 = r17[r19]
            r14 = 1
            if (r2 == r14) goto L_0x0073
            r14 = 2
            if (r2 == r14) goto L_0x0073
            r14 = 3
            if (r2 == r14) goto L_0x0073
            r14 = 4
            if (r2 == r14) goto L_0x0078
        L_0x0073:
            r2 = r18
        L_0x0075:
            r18 = 0
            goto L_0x007f
        L_0x0078:
            r2 = r18
            if (r2 != r14) goto L_0x007d
            goto L_0x0075
        L_0x007d:
            r18 = 1
        L_0x007f:
            int r14 = r0.mVisibility
            r11 = 8
            if (r14 != r11) goto L_0x0089
            r14 = 0
            r18 = 0
            goto L_0x008b
        L_0x0089:
            r14 = r44
        L_0x008b:
            if (r57 == 0) goto L_0x00ae
            if (r12 != 0) goto L_0x0099
            if (r13 != 0) goto L_0x0099
            if (r16 != 0) goto L_0x0099
            r11 = r43
            r10.addEquality(r9, r11)
            goto L_0x00a9
        L_0x0099:
            if (r12 == 0) goto L_0x00a9
            if (r13 != 0) goto L_0x00a9
            int r11 = r41.getMargin()
            r21 = r6
            r6 = 8
            r10.addEquality(r9, r7, r11, r6)
            goto L_0x00b1
        L_0x00a9:
            r21 = r6
            r6 = 8
            goto L_0x00b1
        L_0x00ae:
            r21 = r6
            r6 = r11
        L_0x00b1:
            if (r18 != 0) goto L_0x00dc
            if (r40 == 0) goto L_0x00ca
            r6 = 0
            r11 = 3
            r10.addEquality(r8, r9, r6, r11)
            r6 = 8
            if (r15 <= 0) goto L_0x00c1
            r10.addGreaterThan(r8, r9, r15, r6)
        L_0x00c1:
            r11 = 2147483647(0x7fffffff, float:NaN)
            if (r1 >= r11) goto L_0x00cd
            r10.addLowerThan(r8, r9, r1, r6)
            goto L_0x00cd
        L_0x00ca:
            r10.addEquality(r8, r9, r14, r6)
        L_0x00cd:
            r22 = r3
            r1 = r4
            r14 = r7
            r15 = r8
        L_0x00d2:
            r23 = r18
            r11 = r21
            r18 = r36
        L_0x00d8:
            r21 = r5
            goto L_0x01cf
        L_0x00dc:
            r1 = 2
            if (r5 == r1) goto L_0x0101
            if (r48 != 0) goto L_0x0101
            r1 = 1
            if (r2 == r1) goto L_0x00e6
            if (r2 != 0) goto L_0x0101
        L_0x00e6:
            int r1 = java.lang.Math.max(r3, r14)
            if (r4 <= 0) goto L_0x00f0
            int r1 = java.lang.Math.min(r4, r1)
        L_0x00f0:
            r6 = 8
            r10.addEquality(r8, r9, r1, r6)
            r18 = r36
            r22 = r3
            r1 = r4
            r14 = r7
            r15 = r8
            r11 = r21
            r23 = 0
            goto L_0x00d8
        L_0x0101:
            r1 = -2
            if (r3 != r1) goto L_0x0106
            r11 = r14
            goto L_0x0107
        L_0x0106:
            r11 = r3
        L_0x0107:
            if (r4 != r1) goto L_0x010b
            r1 = r14
            goto L_0x010c
        L_0x010b:
            r1 = r4
        L_0x010c:
            if (r14 <= 0) goto L_0x0112
            r3 = 1
            if (r2 == r3) goto L_0x0112
            r14 = 0
        L_0x0112:
            if (r11 <= 0) goto L_0x011d
            r3 = 8
            r10.addGreaterThan(r8, r9, r11, r3)
            int r14 = java.lang.Math.max(r14, r11)
        L_0x011d:
            if (r1 <= 0) goto L_0x0136
            if (r34 == 0) goto L_0x0126
            r3 = 1
            if (r2 != r3) goto L_0x0126
            r3 = 0
            goto L_0x0127
        L_0x0126:
            r3 = 1
        L_0x0127:
            if (r3 == 0) goto L_0x012f
            r3 = 8
            r10.addLowerThan(r8, r9, r1, r3)
            goto L_0x0131
        L_0x012f:
            r3 = 8
        L_0x0131:
            int r14 = java.lang.Math.min(r14, r1)
            goto L_0x0138
        L_0x0136:
            r3 = 8
        L_0x0138:
            r4 = 1
            if (r2 != r4) goto L_0x0158
            if (r34 == 0) goto L_0x0141
            r10.addEquality(r8, r9, r14, r3)
            goto L_0x0152
        L_0x0141:
            if (r49 == 0) goto L_0x014b
            r4 = 5
            r10.addEquality(r8, r9, r14, r4)
            r10.addLowerThan(r8, r9, r14, r3)
            goto L_0x0152
        L_0x014b:
            r4 = 5
            r10.addEquality(r8, r9, r14, r4)
            r10.addLowerThan(r8, r9, r14, r3)
        L_0x0152:
            r14 = r7
            r15 = r8
            r22 = r11
            goto L_0x00d2
        L_0x0158:
            r14 = 2
            if (r2 != r14) goto L_0x01c1
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = r41.getType()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            if (r3 == r4) goto L_0x0185
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = r41.getType()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            if (r3 != r4) goto L_0x016c
            goto L_0x0185
        L_0x016c:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.getAnchor(r4)
            androidx.constraintlayout.solver.SolverVariable r3 = r10.createObjectVariable(r3)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r6 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.getAnchor(r6)
            androidx.constraintlayout.solver.SolverVariable r4 = r10.createObjectVariable(r4)
            goto L_0x019d
        L_0x0185:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.getAnchor(r4)
            androidx.constraintlayout.solver.SolverVariable r3 = r10.createObjectVariable(r3)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r6 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.getAnchor(r6)
            androidx.constraintlayout.solver.SolverVariable r4 = r10.createObjectVariable(r4)
        L_0x019d:
            r18 = r3
            r6 = r4
            androidx.constraintlayout.solver.ArrayRow r3 = r32.createRow()
            r4 = r8
            r14 = r5
            r5 = r9
            r40 = r11
            r11 = r21
            r21 = r14
            r14 = r7
            r7 = r18
            r15 = r8
            r8 = r56
            androidx.constraintlayout.solver.ArrayRow r3 = r3.createRowDimensionRatio(r4, r5, r6, r7, r8)
            r10.addConstraint(r3)
            r18 = r36
            r22 = r40
            r23 = 0
            goto L_0x01cf
        L_0x01c1:
            r14 = r7
            r15 = r8
            r40 = r11
            r11 = r21
            r21 = r5
            r22 = r40
            r23 = r18
            r18 = 1
        L_0x01cf:
            if (r57 == 0) goto L_0x0410
            if (r49 == 0) goto L_0x01e0
            r1 = r37
            r6 = r38
            r3 = r9
            r2 = r10
            r5 = r15
            r7 = r21
            r4 = 8
            goto L_0x041b
        L_0x01e0:
            if (r12 != 0) goto L_0x01e8
            if (r13 != 0) goto L_0x01e8
            if (r16 != 0) goto L_0x01e8
            goto L_0x03f3
        L_0x01e8:
            if (r12 == 0) goto L_0x01ee
            if (r13 != 0) goto L_0x01ee
            goto L_0x03f3
        L_0x01ee:
            if (r12 != 0) goto L_0x0207
            if (r13 == 0) goto L_0x0207
            int r0 = r42.getMargin()
            int r0 = -r0
            r1 = 8
            r10.addEquality(r15, r11, r0, r1)
            if (r34 == 0) goto L_0x03f3
            r8 = r37
            r0 = 5
            r1 = 0
            r10.addGreaterThan(r9, r8, r1, r0)
            goto L_0x03f3
        L_0x0207:
            r8 = r37
            r7 = 8
            if (r12 == 0) goto L_0x03f3
            if (r13 == 0) goto L_0x03f3
            r12 = r41
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r12.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r13 = r3.mOwner
            r6 = r42
            r3 = 2
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r6.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r4.mOwner
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r31.getParent()
            r16 = 6
            if (r23 == 0) goto L_0x02d6
            if (r2 != 0) goto L_0x025a
            if (r1 != 0) goto L_0x0231
            if (r22 != 0) goto L_0x0231
            r1 = r7
            r3 = r1
            r20 = 0
            r21 = 1
            goto L_0x0237
        L_0x0231:
            r1 = 5
            r3 = 5
            r20 = 1
            r21 = 0
        L_0x0237:
            boolean r7 = r13 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r7 != 0) goto L_0x024d
            boolean r7 = r5 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r7 == 0) goto L_0x0240
            goto L_0x024d
        L_0x0240:
            r7 = r20
            r24 = r21
            r21 = r1
            r20 = r3
            r1 = r16
            r3 = 0
            goto L_0x02e0
        L_0x024d:
            r7 = r20
            r24 = r21
            r3 = 0
            r20 = 4
            r21 = r1
            r1 = r16
            goto L_0x02e0
        L_0x025a:
            r7 = 1
            if (r2 != r7) goto L_0x0267
            r1 = r16
            r3 = 1
            r7 = 1
            r20 = 4
            r21 = 8
            goto L_0x02de
        L_0x0267:
            r7 = 3
            if (r2 != r7) goto L_0x02d1
            int r7 = r0.mResolvedDimensionRatioSide
            r3 = -1
            if (r7 != r3) goto L_0x0283
            if (r50 == 0) goto L_0x0277
            if (r34 == 0) goto L_0x0275
            r1 = 5
            goto L_0x0279
        L_0x0275:
            r1 = 4
            goto L_0x0279
        L_0x0277:
            r1 = 8
        L_0x0279:
            r3 = 1
            r7 = 1
            r20 = 5
            r21 = 8
        L_0x027f:
            r24 = 1
            goto L_0x02e0
        L_0x0283:
            if (r48 == 0) goto L_0x02a2
            r3 = r53
            r7 = 2
            if (r3 == r7) goto L_0x0290
            r1 = 1
            if (r3 != r1) goto L_0x028e
            goto L_0x0290
        L_0x028e:
            r1 = 0
            goto L_0x0291
        L_0x0290:
            r1 = 1
        L_0x0291:
            if (r1 != 0) goto L_0x0297
            r1 = 8
            r3 = 5
            goto L_0x0299
        L_0x0297:
            r1 = 5
            r3 = 4
        L_0x0299:
            r21 = r1
            r20 = r3
            r1 = r16
            r3 = 1
            r7 = 1
            goto L_0x027f
        L_0x02a2:
            if (r1 <= 0) goto L_0x02ab
            r1 = r16
            r3 = 1
            r7 = 1
            r20 = 5
            goto L_0x02ce
        L_0x02ab:
            if (r1 != 0) goto L_0x02c8
            if (r22 != 0) goto L_0x02c8
            if (r50 != 0) goto L_0x02b8
            r1 = r16
            r3 = 1
            r7 = 1
            r20 = 8
            goto L_0x02ce
        L_0x02b8:
            if (r13 == r4) goto L_0x02be
            if (r5 == r4) goto L_0x02be
            r1 = 4
            goto L_0x02bf
        L_0x02be:
            r1 = 5
        L_0x02bf:
            r21 = r1
            r1 = r16
            r3 = 1
            r7 = 1
            r20 = 4
            goto L_0x027f
        L_0x02c8:
            r1 = r16
            r3 = 1
            r7 = 1
            r20 = 4
        L_0x02ce:
            r21 = 5
            goto L_0x027f
        L_0x02d1:
            r1 = r16
            r3 = 0
            r7 = 0
            goto L_0x02da
        L_0x02d6:
            r1 = r16
            r3 = 1
            r7 = 1
        L_0x02da:
            r20 = 4
            r21 = 5
        L_0x02de:
            r24 = 0
        L_0x02e0:
            if (r3 == 0) goto L_0x02eb
            if (r14 != r11) goto L_0x02eb
            if (r13 == r4) goto L_0x02eb
            r25 = 0
            r26 = 0
            goto L_0x02ef
        L_0x02eb:
            r25 = r3
            r26 = 1
        L_0x02ef:
            if (r7 == 0) goto L_0x0321
            int r3 = r0.mVisibility
            r7 = 8
            if (r3 != r7) goto L_0x02fa
            r27 = 4
            goto L_0x02fc
        L_0x02fa:
            r27 = r1
        L_0x02fc:
            int r28 = r41.getMargin()
            int r29 = r42.getMargin()
            r1 = r32
            r3 = r2
            r2 = r9
            r12 = r3
            r3 = r14
            r33 = r12
            r12 = r4
            r4 = r28
            r28 = r12
            r12 = r5
            r5 = r47
            r6 = r11
            r10 = r7
            r7 = r15
            r8 = r29
            r30 = r9
            r9 = r27
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x032a
        L_0x0321:
            r33 = r2
            r28 = r4
            r12 = r5
            r30 = r9
            r10 = 8
        L_0x032a:
            int r0 = r0.mVisibility
            if (r0 != r10) goto L_0x032f
            return
        L_0x032f:
            if (r25 == 0) goto L_0x035b
            if (r34 == 0) goto L_0x0342
            if (r14 == r11) goto L_0x0342
            if (r23 != 0) goto L_0x0342
            boolean r0 = r13 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r0 != 0) goto L_0x033f
            boolean r0 = r12 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r0 == 0) goto L_0x0342
        L_0x033f:
            r0 = r16
            goto L_0x0344
        L_0x0342:
            r0 = r21
        L_0x0344:
            int r1 = r41.getMargin()
            r2 = r32
            r4 = r10
            r3 = r30
            r2.addGreaterThan(r3, r14, r1, r0)
            int r1 = r42.getMargin()
            int r1 = -r1
            r2.addLowerThan(r15, r11, r1, r0)
            r21 = r0
            goto L_0x0360
        L_0x035b:
            r2 = r32
            r4 = r10
            r3 = r30
        L_0x0360:
            if (r34 == 0) goto L_0x0372
            if (r51 == 0) goto L_0x0372
            boolean r0 = r13 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r0 != 0) goto L_0x0372
            boolean r0 = r12 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r0 != 0) goto L_0x0372
            r0 = r16
            r1 = r0
            r26 = 1
            goto L_0x0376
        L_0x0372:
            r0 = r20
            r1 = r21
        L_0x0376:
            if (r26 == 0) goto L_0x03c6
            if (r24 == 0) goto L_0x03a6
            if (r50 == 0) goto L_0x037e
            if (r35 == 0) goto L_0x03a6
        L_0x037e:
            r5 = r28
            if (r13 == r5) goto L_0x0387
            if (r12 != r5) goto L_0x0385
            goto L_0x0387
        L_0x0385:
            r16 = r0
        L_0x0387:
            boolean r6 = r13 instanceof androidx.constraintlayout.solver.widgets.Guideline
            if (r6 != 0) goto L_0x038f
            boolean r6 = r12 instanceof androidx.constraintlayout.solver.widgets.Guideline
            if (r6 == 0) goto L_0x0391
        L_0x038f:
            r16 = 5
        L_0x0391:
            boolean r6 = r13 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r6 != 0) goto L_0x0399
            boolean r6 = r12 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r6 == 0) goto L_0x039b
        L_0x0399:
            r16 = 5
        L_0x039b:
            if (r50 == 0) goto L_0x039f
            r6 = 5
            goto L_0x03a1
        L_0x039f:
            r6 = r16
        L_0x03a1:
            int r0 = java.lang.Math.max(r6, r0)
            goto L_0x03a8
        L_0x03a6:
            r5 = r28
        L_0x03a8:
            if (r34 == 0) goto L_0x03b7
            int r0 = java.lang.Math.min(r1, r0)
            if (r48 == 0) goto L_0x03b7
            if (r50 != 0) goto L_0x03b7
            if (r13 == r5) goto L_0x03b6
            if (r12 != r5) goto L_0x03b7
        L_0x03b6:
            r0 = 4
        L_0x03b7:
            int r1 = r41.getMargin()
            r2.addEquality(r3, r14, r1, r0)
            int r1 = r42.getMargin()
            int r1 = -r1
            r2.addEquality(r15, r11, r1, r0)
        L_0x03c6:
            if (r34 == 0) goto L_0x03d8
            r1 = r37
            if (r1 != r14) goto L_0x03d1
            int r0 = r41.getMargin()
            goto L_0x03d2
        L_0x03d1:
            r0 = 0
        L_0x03d2:
            if (r14 == r1) goto L_0x03d8
            r5 = 5
            r2.addGreaterThan(r3, r1, r0, r5)
        L_0x03d8:
            if (r34 == 0) goto L_0x03f4
            if (r23 == 0) goto L_0x03f4
            r5 = r15
            if (r45 != 0) goto L_0x03f5
            if (r22 != 0) goto L_0x03f5
            if (r23 == 0) goto L_0x03ed
            r0 = r33
            r1 = 3
            if (r0 != r1) goto L_0x03ed
            r0 = 0
            r2.addGreaterThan(r5, r3, r0, r4)
            goto L_0x03f5
        L_0x03ed:
            r0 = 0
            r1 = 5
            r2.addGreaterThan(r5, r3, r0, r1)
            goto L_0x03f5
        L_0x03f3:
            r2 = r10
        L_0x03f4:
            r5 = r15
        L_0x03f5:
            if (r34 == 0) goto L_0x040f
            if (r18 == 0) goto L_0x040f
            r0 = r42
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r0.mTarget
            if (r1 == 0) goto L_0x0406
            int r14 = r42.getMargin()
            r6 = r38
            goto L_0x0409
        L_0x0406:
            r6 = r38
            r14 = 0
        L_0x0409:
            if (r11 == r6) goto L_0x040f
            r0 = 5
            r2.addGreaterThan(r6, r5, r14, r0)
        L_0x040f:
            return
        L_0x0410:
            r1 = r37
            r6 = r38
            r3 = r9
            r2 = r10
            r5 = r15
            r4 = 8
            r7 = r21
        L_0x041b:
            r8 = 2
            if (r7 >= r8) goto L_0x0463
            if (r34 == 0) goto L_0x0463
            if (r18 == 0) goto L_0x0463
            r7 = 0
            r2.addGreaterThan(r3, r1, r7, r4)
            if (r33 != 0) goto L_0x0431
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r0.mBaseline
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 != 0) goto L_0x042f
            goto L_0x0431
        L_0x042f:
            r1 = 0
            goto L_0x0432
        L_0x0431:
            r1 = 1
        L_0x0432:
            if (r33 != 0) goto L_0x045c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.mBaseline
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x045c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mBaseline
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r0.mOwner
            float r1 = r0.mDimensionRatio
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x045a
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r0.mListDimensionBehaviors
            r3 = 0
            r1 = r1[r3]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r3) goto L_0x045a
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r1 = 1
            r0 = r0[r1]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r0 != r3) goto L_0x045a
            goto L_0x045c
        L_0x045a:
            r14 = 0
            goto L_0x045d
        L_0x045c:
            r14 = r1
        L_0x045d:
            if (r14 == 0) goto L_0x0463
            r0 = 0
            r2.addGreaterThan(r6, r5, r0, r4)
        L_0x0463:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.applyConstraints(androidx.constraintlayout.solver.LinearSystem, boolean, boolean, boolean, boolean, androidx.constraintlayout.solver.SolverVariable, androidx.constraintlayout.solver.SolverVariable, androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour, boolean, androidx.constraintlayout.solver.widgets.ConstraintAnchor, androidx.constraintlayout.solver.widgets.ConstraintAnchor, int, int, int, int, float, boolean, boolean, boolean, boolean, int, int, int, int, float, boolean):void");
    }

    /* renamed from: androidx.constraintlayout.solver.widgets.ConstraintWidget$1 */
    static /* synthetic */ class C01551 {

        /* renamed from: $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type */
        static final /* synthetic */ int[] f37x4c44d048;

        /* renamed from: $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour */
        static final /* synthetic */ int[] f38xdde91696;

        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(31:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0083 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x008f */
        static {
            /*
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f38xdde91696 = r0
                r1 = 1
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = f38xdde91696     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = f38xdde91696     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = f38xdde91696     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type[] r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                f37x4c44d048 = r4
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r5 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                int[] r1 = f37x4c44d048     // Catch:{ NoSuchFieldError -> 0x004e }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP     // Catch:{ NoSuchFieldError -> 0x004e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r0 = f37x4c44d048     // Catch:{ NoSuchFieldError -> 0x0058 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = f37x4c44d048     // Catch:{ NoSuchFieldError -> 0x0062 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = f37x4c44d048     // Catch:{ NoSuchFieldError -> 0x006d }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BASELINE     // Catch:{ NoSuchFieldError -> 0x006d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006d }
            L_0x006d:
                int[] r0 = f37x4c44d048     // Catch:{ NoSuchFieldError -> 0x0078 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = f37x4c44d048     // Catch:{ NoSuchFieldError -> 0x0083 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_X     // Catch:{ NoSuchFieldError -> 0x0083 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0083 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0083 }
            L_0x0083:
                int[] r0 = f37x4c44d048     // Catch:{ NoSuchFieldError -> 0x008f }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_Y     // Catch:{ NoSuchFieldError -> 0x008f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008f }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x008f }
            L_0x008f:
                int[] r0 = f37x4c44d048     // Catch:{ NoSuchFieldError -> 0x009b }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.NONE     // Catch:{ NoSuchFieldError -> 0x009b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009b }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009b }
            L_0x009b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.C01551.<clinit>():void");
        }
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        int objectVariableValue = linearSystem.getObjectVariableValue(this.mLeft);
        int objectVariableValue2 = linearSystem.getObjectVariableValue(this.mTop);
        int objectVariableValue3 = linearSystem.getObjectVariableValue(this.mRight);
        int objectVariableValue4 = linearSystem.getObjectVariableValue(this.mBottom);
        if (this.horizontalRun.start.resolved && this.horizontalRun.end.resolved) {
            objectVariableValue = this.horizontalRun.start.value;
            objectVariableValue3 = this.horizontalRun.end.value;
        }
        if (this.verticalRun.start.resolved && this.verticalRun.end.resolved) {
            objectVariableValue2 = this.verticalRun.start.value;
            objectVariableValue4 = this.verticalRun.end.value;
        }
        int i = objectVariableValue4 - objectVariableValue2;
        if (objectVariableValue3 - objectVariableValue < 0 || i < 0 || objectVariableValue == Integer.MIN_VALUE || objectVariableValue == Integer.MAX_VALUE || objectVariableValue2 == Integer.MIN_VALUE || objectVariableValue2 == Integer.MAX_VALUE || objectVariableValue3 == Integer.MIN_VALUE || objectVariableValue3 == Integer.MAX_VALUE || objectVariableValue4 == Integer.MIN_VALUE || objectVariableValue4 == Integer.MAX_VALUE) {
            objectVariableValue4 = 0;
            objectVariableValue = 0;
            objectVariableValue2 = 0;
            objectVariableValue3 = 0;
        }
        setFrame(objectVariableValue, objectVariableValue2, objectVariableValue3, objectVariableValue4);
    }

    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        this.mHorizontalResolution = constraintWidget.mHorizontalResolution;
        this.mVerticalResolution = constraintWidget.mVerticalResolution;
        this.mMatchConstraintDefaultWidth = constraintWidget.mMatchConstraintDefaultWidth;
        this.mMatchConstraintDefaultHeight = constraintWidget.mMatchConstraintDefaultHeight;
        int[] iArr = this.mResolvedMatchConstraintDefault;
        int[] iArr2 = constraintWidget.mResolvedMatchConstraintDefault;
        iArr[0] = iArr2[0];
        iArr[1] = iArr2[1];
        this.mMatchConstraintMinWidth = constraintWidget.mMatchConstraintMinWidth;
        this.mMatchConstraintMaxWidth = constraintWidget.mMatchConstraintMaxWidth;
        this.mMatchConstraintMinHeight = constraintWidget.mMatchConstraintMinHeight;
        this.mMatchConstraintMaxHeight = constraintWidget.mMatchConstraintMaxHeight;
        this.mMatchConstraintPercentHeight = constraintWidget.mMatchConstraintPercentHeight;
        this.mIsWidthWrapContent = constraintWidget.mIsWidthWrapContent;
        this.mIsHeightWrapContent = constraintWidget.mIsHeightWrapContent;
        this.mResolvedDimensionRatioSide = constraintWidget.mResolvedDimensionRatioSide;
        this.mResolvedDimensionRatio = constraintWidget.mResolvedDimensionRatio;
        int[] iArr3 = constraintWidget.mMaxDimension;
        this.mMaxDimension = Arrays.copyOf(iArr3, iArr3.length);
        this.mCircleConstraintAngle = constraintWidget.mCircleConstraintAngle;
        this.hasBaseline = constraintWidget.hasBaseline;
        this.inPlaceholder = constraintWidget.inPlaceholder;
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mListDimensionBehaviors = (DimensionBehaviour[]) Arrays.copyOf(this.mListDimensionBehaviors, 2);
        ConstraintWidget constraintWidget2 = null;
        this.mParent = this.mParent == null ? null : hashMap.get(constraintWidget.mParent);
        this.mWidth = constraintWidget.mWidth;
        this.mHeight = constraintWidget.mHeight;
        this.mDimensionRatio = constraintWidget.mDimensionRatio;
        this.mDimensionRatioSide = constraintWidget.mDimensionRatioSide;
        this.f35mX = constraintWidget.f35mX;
        this.f36mY = constraintWidget.f36mY;
        this.mRelX = constraintWidget.mRelX;
        this.mRelY = constraintWidget.mRelY;
        this.mOffsetX = constraintWidget.mOffsetX;
        this.mOffsetY = constraintWidget.mOffsetY;
        this.mBaselineDistance = constraintWidget.mBaselineDistance;
        this.mMinWidth = constraintWidget.mMinWidth;
        this.mMinHeight = constraintWidget.mMinHeight;
        this.mHorizontalBiasPercent = constraintWidget.mHorizontalBiasPercent;
        this.mVerticalBiasPercent = constraintWidget.mVerticalBiasPercent;
        this.mCompanionWidget = constraintWidget.mCompanionWidget;
        this.mContainerItemSkip = constraintWidget.mContainerItemSkip;
        this.mVisibility = constraintWidget.mVisibility;
        this.mDebugName = constraintWidget.mDebugName;
        this.mType = constraintWidget.mType;
        this.mDistToTop = constraintWidget.mDistToTop;
        this.mDistToLeft = constraintWidget.mDistToLeft;
        this.mDistToRight = constraintWidget.mDistToRight;
        this.mDistToBottom = constraintWidget.mDistToBottom;
        this.mLeftHasCentered = constraintWidget.mLeftHasCentered;
        this.mRightHasCentered = constraintWidget.mRightHasCentered;
        this.mTopHasCentered = constraintWidget.mTopHasCentered;
        this.mBottomHasCentered = constraintWidget.mBottomHasCentered;
        this.mHorizontalWrapVisited = constraintWidget.mHorizontalWrapVisited;
        this.mVerticalWrapVisited = constraintWidget.mVerticalWrapVisited;
        this.mOptimizerMeasurable = constraintWidget.mOptimizerMeasurable;
        this.mGroupsToSolver = constraintWidget.mGroupsToSolver;
        this.mHorizontalChainStyle = constraintWidget.mHorizontalChainStyle;
        this.mVerticalChainStyle = constraintWidget.mVerticalChainStyle;
        this.mHorizontalChainFixedPosition = constraintWidget.mHorizontalChainFixedPosition;
        this.mVerticalChainFixedPosition = constraintWidget.mVerticalChainFixedPosition;
        float[] fArr = this.mWeight;
        float[] fArr2 = constraintWidget.mWeight;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
        ConstraintWidget[] constraintWidgetArr = this.mListNextMatchConstraintsWidget;
        ConstraintWidget[] constraintWidgetArr2 = constraintWidget.mListNextMatchConstraintsWidget;
        constraintWidgetArr[0] = constraintWidgetArr2[0];
        constraintWidgetArr[1] = constraintWidgetArr2[1];
        ConstraintWidget[] constraintWidgetArr3 = this.mNextChainWidget;
        ConstraintWidget[] constraintWidgetArr4 = constraintWidget.mNextChainWidget;
        constraintWidgetArr3[0] = constraintWidgetArr4[0];
        constraintWidgetArr3[1] = constraintWidgetArr4[1];
        ConstraintWidget constraintWidget3 = constraintWidget.mHorizontalNextWidget;
        this.mHorizontalNextWidget = constraintWidget3 == null ? null : hashMap.get(constraintWidget3);
        ConstraintWidget constraintWidget4 = constraintWidget.mVerticalNextWidget;
        if (constraintWidget4 != null) {
            constraintWidget2 = hashMap.get(constraintWidget4);
        }
        this.mVerticalNextWidget = constraintWidget2;
    }

    public void updateFromRuns(boolean z, boolean z2) {
        int i;
        int i2;
        boolean isResolved = z & this.horizontalRun.isResolved();
        boolean isResolved2 = z2 & this.verticalRun.isResolved();
        int i3 = this.horizontalRun.start.value;
        int i4 = this.verticalRun.start.value;
        int i5 = this.horizontalRun.end.value;
        int i6 = this.verticalRun.end.value;
        int i7 = i6 - i4;
        if (i5 - i3 < 0 || i7 < 0 || i3 == Integer.MIN_VALUE || i3 == Integer.MAX_VALUE || i4 == Integer.MIN_VALUE || i4 == Integer.MAX_VALUE || i5 == Integer.MIN_VALUE || i5 == Integer.MAX_VALUE || i6 == Integer.MIN_VALUE || i6 == Integer.MAX_VALUE) {
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
        }
        int i8 = i5 - i3;
        int i9 = i6 - i4;
        if (isResolved) {
            this.f35mX = i3;
        }
        if (isResolved2) {
            this.f36mY = i4;
        }
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (isResolved) {
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i8 < (i2 = this.mWidth)) {
                i8 = i2;
            }
            this.mWidth = i8;
            int i10 = this.mMinWidth;
            if (i8 < i10) {
                this.mWidth = i10;
            }
        }
        if (isResolved2) {
            if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i9 < (i = this.mHeight)) {
                i9 = i;
            }
            this.mHeight = i9;
            int i11 = this.mMinHeight;
            if (i9 < i11) {
                this.mHeight = i11;
            }
        }
    }
}
