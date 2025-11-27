package com.example.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun DialogScreen() {
    var showAlertDialog by remember { mutableStateOf(false) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showCustomDialog by remember { mutableStateOf(false) }
    var showInputDialog by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var showLoadingDialog by remember { mutableStateOf(false) }
    var showListDialog by remember { mutableStateOf(false) }
    var showFullScreenDialog by remember { mutableStateOf(false) }

    var dialogResult by remember { mutableStateOf("") }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Dialog 학습",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        )

        Text(
            text = "다양한 종류의 Dialog를 학습합니다",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 결과 표시
        if (dialogResult.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors =
                    CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    ),
            ) {
                Text(
                    text = "결과: $dialogResult",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 1. AlertDialog
        DialogCard(
            title = "1. AlertDialog",
            description = "기본적인 알림 다이얼로그",
            emoji = "⚠️",
            onClick = { showAlertDialog = true },
        )

        // 2. Confirm Dialog
        DialogCard(
            title = "2. Confirm Dialog",
            description = "확인/취소 선택 다이얼로그",
            emoji = "❓",
            onClick = { showConfirmDialog = true },
        )

        // 3. Custom Dialog
        DialogCard(
            title = "3. Custom Dialog",
            description = "커스텀 디자인 다이얼로그",
            emoji = "🎨",
            onClick = { showCustomDialog = true },
        )

        // 4. Input Dialog
        DialogCard(
            title = "4. Input Dialog",
            description = "사용자 입력 받는 다이얼로그",
            emoji = "✏️",
            onClick = { showInputDialog = true },
        )

        // 5. Bottom Sheet
        DialogCard(
            title = "5. Bottom Sheet",
            description = "하단에서 올라오는 시트",
            emoji = "📋",
            onClick = { showBottomSheet = true },
        )

        // 6. Loading Dialog
        DialogCard(
            title = "6. Loading Dialog",
            description = "로딩 표시 다이얼로그",
            emoji = "⏳",
            onClick = { showLoadingDialog = true },
        )

        // 7. List Dialog
        DialogCard(
            title = "7. List Dialog",
            description = "항목 선택 다이얼로그",
            emoji = "📝",
            onClick = { showListDialog = true },
        )

        // 8. Full Screen Dialog
        DialogCard(
            title = "8. Full Screen Dialog",
            description = "전체 화면 다이얼로그",
            emoji = "🖼️",
            onClick = { showFullScreenDialog = true },
        )
    }

    // Dialog 표시
    if (showAlertDialog) {
        SimpleAlertDialog(
            onDismiss = {
                showAlertDialog = false
                dialogResult = "AlertDialog 닫힘"
            },
        )
    }

    if (showConfirmDialog) {
        ConfirmDialog(
            onConfirm = {
                showConfirmDialog = false
                dialogResult = "확인 선택"
            },
            onDismiss = {
                showConfirmDialog = false
                dialogResult = "취소 선택"
            },
        )
    }

    if (showCustomDialog) {
        CustomDialog(
            onDismiss = {
                showCustomDialog = false
                dialogResult = "Custom Dialog 닫힘"
            },
        )
    }

    if (showInputDialog) {
        InputDialog(
            onConfirm = { input ->
                showInputDialog = false
                dialogResult = "입력: $input"
            },
            onDismiss = {
                showInputDialog = false
                dialogResult = "입력 취소"
            },
        )
    }

    if (showBottomSheet) {
        BottomSheetDialog(
            onDismiss = {
                showBottomSheet = false
                dialogResult = "Bottom Sheet 닫힘"
            },
            onItemClick = { item ->
                showBottomSheet = false
                dialogResult = "선택: $item"
            },
        )
    }

    if (showLoadingDialog) {
        LoadingDialog()
        // 3초 후 자동으로 닫기
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(3000)
            showLoadingDialog = false
            dialogResult = "로딩 완료"
        }
    }

    if (showListDialog) {
        ListDialog(
            onItemClick = { item ->
                showListDialog = false
                dialogResult = "선택: $item"
            },
            onDismiss = {
                showListDialog = false
                dialogResult = "선택 취소"
            },
        )
    }

    if (showFullScreenDialog) {
        FullScreenDialog(
            onDismiss = {
                showFullScreenDialog = false
                dialogResult = "Full Screen Dialog 닫힘"
            },
        )
    }
}

@Composable
fun DialogCard(
    title: String,
    description: String,
    emoji: String,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = emoji,
                fontSize = 32.sp,
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

// 1. Simple AlertDialog
@Composable
fun SimpleAlertDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(Icons.Default.Info, contentDescription = null)
        },
        title = {
            Text("AlertDialog")
        },
        text = {
            Text("기본적인 알림 다이얼로그입니다.\nAlertDialog는 사용자에게 중요한 정보를 전달할 때 사용합니다.")
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("확인")
            }
        },
    )
}

// 2. Confirm Dialog
@Composable
fun ConfirmDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(Icons.Default.Warning, contentDescription = null)
        },
        title = {
            Text("확인이 필요합니다")
        },
        text = {
            Text("정말 삭제하시겠습니까?\n이 작업은 되돌릴 수 없습니다.")
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                colors =
                    ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.error,
                    ),
            ) {
                Text("확인")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("취소")
            }
        },
    )
}

// 3. Custom Dialog
@Composable
fun CustomDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            shape = MaterialTheme.shapes.large,
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )

                Text(
                    text = "커스텀 다이얼로그",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = "원하는 대로 디자인할 수 있습니다.",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                    ) {
                        Text("취소")
                    }
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                    ) {
                        Text("확인")
                    }
                }
            }
        }
    }
}

// 4. Input Dialog
@Composable
fun InputDialog(
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    var text by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("이름 입력")
        },
        text = {
            Column {
                Text("이름을 입력해주세요:")
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("이름") },
                    singleLine = true,
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm(text) },
                enabled = text.isNotBlank(),
            ) {
                Text("확인")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("취소")
            }
        },
    )
}

// 5. Bottom Sheet Dialog
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDialog(
    onDismiss: () -> Unit,
    onItemClick: (String) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = "옵션 선택",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp),
            )

            val items =
                listOf(
                    "공유하기" to Icons.Default.Share,
                    "좋아요" to Icons.Default.ThumbUp,
                    "즐겨찾기" to Icons.Default.Star,
                    "삭제" to Icons.Default.Delete,
                )

            items.forEach { (label, icon) ->
                ListItem(
                    headlineContent = { Text(label) },
                    leadingContent = {
                        Icon(icon, contentDescription = null)
                    },
                    modifier =
                        Modifier.clickable {
                            onItemClick(label)
                        },
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// 6. Loading Dialog
@Composable
fun LoadingDialog() {
    Dialog(onDismissRequest = {}) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = MaterialTheme.shapes.large,
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                CircularProgressIndicator()
                Text(
                    text = "로딩 중...",
                    fontSize = 16.sp,
                )
            }
        }
    }
}

// 7. List Dialog
@Composable
fun ListDialog(
    onItemClick: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    val items = listOf("사과", "바나나", "오렌지", "포도", "딸기", "수박", "멜론")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("과일 선택")
        },
        text = {
            Column {
                items.forEach { item ->
                    ListItem(
                        headlineContent = { Text(item) },
                        modifier =
                            Modifier.clickable {
                                onItemClick(item)
                            },
                    )
                    if (item != items.last()) {
                        HorizontalDivider()
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("취소")
            }
        },
    )
}

// 8. Full Screen Dialog
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("전체 화면 다이얼로그") },
                    navigationIcon = {
                        IconButton(onClick = onDismiss) {
                            Icon(Icons.Default.Close, "닫기")
                        }
                    },
                )
            },
        ) { padding ->
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = "전체 화면 다이얼로그",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text =
                        "전체 화면을 사용하는 다이얼로그입니다.\n" +
                            "복잡한 UI가 필요할 때 사용합니다.",
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("완료")
                }
            }
        }
    }
}
