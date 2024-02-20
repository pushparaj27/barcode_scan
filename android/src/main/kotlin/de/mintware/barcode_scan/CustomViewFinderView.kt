import android.content.Context
import android.graphics.Color
import me.dm7.barcodescanner.core.ViewFinderView

class CustomViewFinderView(context: Context) : ViewFinderView(context) {

    init {
        // Customize laser color
        setLaserColor(Color.BLUE)

        // Customize mask color
        setMaskColor(Color.parseColor("#80000000")) // Transparent black

        // Customize border color
        setBorderColor(Color.GREEN)

        // Customize border stroke width
        setBorderStrokeWidth(5)

        // Customize border corner radius
        setBorderCornerRounded(true)

        // Customize border line length
        setBorderLineLength(20)

        // Set whether to use square view finder
        setSquareViewFinder(true)

        // Set view finder offset if needed
        setViewFinderOffset(50)

    }
}
